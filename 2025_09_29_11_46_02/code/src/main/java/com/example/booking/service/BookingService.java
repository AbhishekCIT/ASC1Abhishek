package com.example.booking.service;

import com.example.booking.client.PaymentGatewayClient;
import com.example.booking.client.EmailService;
import com.example.booking.exception.InvalidPassengerDetailsException;
import com.example.booking.exception.SeatUnavailableException;
import com.example.booking.exception.PaymentFailedException;
import com.example.booking.model.Booking;
import com.example.booking.model.BookingRequest;
import com.example.booking.model.BookingResponse;
import com.example.booking.model.PassengerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for booking flights and handling business logic.
 */
@Service
public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private PaymentGatewayClient paymentGatewayClient;
    @Autowired
    private EmailService emailService;

    /**
     * Creates a booking after validating details, processing payment, and sending confirmation.
     * @param request Booking request
     * @return Booking confirmation response
     */
    public BookingResponse createBooking(BookingRequest request) {
        validatePassengerDetails(request.getPassengerDetails());
        checkSeatAvailability(request.getFlightId(), request.getSeatSelection());
        boolean paymentSuccess = paymentGatewayClient.processPayment(request.getPaymentInfo());
        if (!paymentSuccess) {
            logger.error("Payment failed for booking request: {}", request);
            throw new PaymentFailedException("Payment failed. Booking not confirmed.");
        }
        // Generate booking reference and eTicket (simulate PDF/URL)
        String bookingReference = generateBookingReference();
        String eTicket = "https://etickets.example.com/" + bookingReference + ".pdf";
        Booking booking = new Booking(bookingReference, "CONFIRMED", request.getFlightId(), request.getPassengerDetails(), request.getSeatSelection(), eTicket);
        // Send confirmation email
        emailService.sendConfirmation(booking);
        return new BookingResponse(bookingReference, "CONFIRMED", eTicket);
    }

    /**
     * Validates passenger details.
     */
    private void validatePassengerDetails(List<PassengerDetails> passengerDetails) {
        if (passengerDetails == null || passengerDetails.isEmpty()) {
            throw new InvalidPassengerDetailsException("Passenger details are required");
        }
        for (PassengerDetails p : passengerDetails) {
            if (p.getName() == null || p.getName().trim().isEmpty() || p.getPassport() == null || p.getPassport().trim().isEmpty()) {
                throw new InvalidPassengerDetailsException("Invalid passenger details");
            }
        }
    }

    /**
     * Checks seat availability (simulated as always available except seat '13B').
     */
    private void checkSeatAvailability(String flightId, List<String> seatSelection) {
        if (seatSelection != null && seatSelection.contains("13B")) {
            throw new SeatUnavailableException("Selected seat not available");
        }
    }

    /**
     * Generates a unique booking reference.
     */
    private String generateBookingReference() {
        return "BR" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
