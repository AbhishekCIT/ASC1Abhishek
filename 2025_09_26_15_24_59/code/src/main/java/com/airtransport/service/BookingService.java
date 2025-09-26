package com.airtransport.service;

import com.airtransport.client.ExternalFlightAPIClient;
import com.airtransport.exception.BookingFailedException;
import com.airtransport.exception.PaymentFailedException;
import com.airtransport.model.BookingRequest;
import com.airtransport.model.BookingResponse;
import com.airtransport.model.PaymentInfo;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.FlightRepository;
import com.airtransport.repository.PassengerRepository;
import com.airtransport.repository.PaymentRepository;
import com.airtransport.util.BookingRefUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for handling booking logic, payment, seat reservation, and confirmation.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final PaymentService paymentService;
    private final EmailNotificationService emailNotificationService;
    private final ExternalFlightAPIClient externalFlightAPIClient;
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final PaymentRepository paymentRepository;
    private final FlightRepository flightRepository;
    private final BookingRefUtil bookingRefUtil;

    /**
     * Books a flight for the user, processes payment, reserves seat, and sends confirmation.
     * @param request Booking request
     * @return Booking response
     */
    @Transactional
    public BookingResponse bookFlight(BookingRequest request) {
        // Validate passenger details
        if (request.getPassengerDetails() == null || request.getPassengerDetails().isEmpty()) {
            throw new BookingFailedException("Invalid passenger details");
        }
        // Validate flight exists
        var flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new BookingFailedException("Flight not found"));
        // Process payment
        PaymentInfo paymentInfo = request.getPaymentInfo();
        boolean paymentStatus = paymentService.processPayment(paymentInfo, flight.getPrice());
        if (!paymentStatus) {
            logger.error("Payment failed for booking request: {}", request);
            throw new PaymentFailedException("Payment validation failed.");
        }
        // Reserve seat with external API
        boolean reservationStatus = externalFlightAPIClient.reserveSeat(request.getFlightId(), request.getPassengerDetails());
        if (!reservationStatus) {
            logger.error("Seat reservation failed for flight: {}", request.getFlightId());
            throw new BookingFailedException("Booking could not be completed");
        }
        // Save booking
        String bookingRef = bookingRefUtil.generateBookingRef();
        var booking = bookingRepository.save(request.toBookingEntity(bookingRef, LocalDateTime.now()));
        // Save passengers
        passengerRepository.saveAll(request.toPassengerEntities(bookingRef));
        // Save payment
        paymentRepository.save(paymentService.toPaymentEntity(bookingRef, paymentInfo, flight.getPrice()));
        // Send confirmation email
        emailNotificationService.sendConfirmationEmail(request.getUserId(), bookingRef);
        logger.info("Booking {} confirmed for user {}", bookingRef, request.getUserId());
        return BookingResponse.success(bookingRef, booking.getStatus(), booking);
    }
}
