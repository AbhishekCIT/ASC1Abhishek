package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.BookingRepository;
import com.example.flightbooking.repository.FlightRepository;
import com.example.flightbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for handling booking logic and seat reservation.
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private GDSClient gdsClient;

    @Autowired
    private NotificationService notificationService;

    /**
     * Books a flight for a user, reserves a seat, processes payment, and sends confirmation.
     * @param bookingRequest the booking request
     * @return booking confirmation
     */
    public BookingConfirmation bookFlight(BookingRequest bookingRequest) {
        // Validate input
        ValidationUtil.validateBookingRequest(bookingRequest);
        Flight flight = flightRepository.findById(bookingRequest.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        if (flight.getSeatsAvailable() <= 0) {
            throw new IllegalArgumentException("No seats available");
        }
        // Reserve seat via GDS
        boolean seatReserved = gdsClient.reserveSeat(bookingRequest.getFlightId());
        if (!seatReserved) {
            throw new IllegalArgumentException("Failed to reserve seat");
        }
        // Authorize payment
        boolean paymentAuthorized = paymentService.authorizePayment(bookingRequest.getPaymentInfo(), flight.getPrice());
        if (!paymentAuthorized) {
            throw new IllegalArgumentException("Payment authorization failed");
        }
        // Reduce seat count
        flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
        flightRepository.save(flight);
        // Create booking
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setUserId(bookingRequest.getUserId());
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setPnr(generatePNR());
        bookingRepository.save(booking);
        // Send confirmation notification
        notificationService.sendBookingConfirmation(booking);
        // Return confirmation
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(booking.getBookingId());
        confirmation.setStatus(booking.getStatus());
        confirmation.setPnr(booking.getPnr());
        return confirmation;
    }

    private String generatePNR() {
        // Simple PNR generation logic (can be replaced with a better one)
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
