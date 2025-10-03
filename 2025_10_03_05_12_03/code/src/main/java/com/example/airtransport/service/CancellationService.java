package com.example.airtransport.service;

import com.example.airtransport.model.CancellationRequest;
import com.example.airtransport.model.CancellationResponse;
import com.example.airtransport.model.Booking;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.repository.FlightRepository;
import com.example.airtransport.exception.IneligibleBookingException;
import com.example.airtransport.exception.RefundCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for cancellation and refund logic.
 */
@Service
public class CancellationService {
    private static final Logger logger = LoggerFactory.getLogger(CancellationService.class);

    @Autowired
    private RefundService refundService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private EmailService emailService;

    /**
     * Cancels a booking, processes refund, updates status, and sends confirmation email.
     * @param request Cancellation request
     * @return Cancellation confirmation response
     */
    public CancellationResponse cancelBooking(CancellationRequest request) {
        // Find booking by reference
        Booking booking = bookingRepository.findByReference(request.getBookingReference());
        if (booking == null || !isEligibleForCancellation(booking)) {
            throw new IneligibleBookingException("Booking not eligible for cancellation or not found.");
        }
        // Calculate refund
        double refundAmount = calculateRefund(booking);
        // Release seats
        flightRepository.releaseSeats(booking.getFlightId(), booking.getSeats());
        // Process refund
        boolean refundSuccess = refundService.processRefund(booking, refundAmount);
        if (!refundSuccess) {
            throw new com.example.airtransport.exception.RefundFailedException("Refund could not be processed.");
        }
        // Update booking status
        bookingRepository.updateStatus(booking.getId(), "CANCELLED", refundAmount);
        // Send cancellation email
        emailService.sendCancellationEmail(booking, refundAmount);
        // Build response
        CancellationResponse response = new CancellationResponse("CANCELLED", refundAmount, "Standard", "sent");
        return response;
    }

    private boolean isEligibleForCancellation(Booking booking) {
        // Example: Only allow cancellation if status is CONFIRMED
        return "CONFIRMED".equalsIgnoreCase(booking.getStatus());
    }

    private double calculateRefund(Booking booking) {
        // Example: Refund 80% of amount for standard policy
        try {
            return booking.getAmount() * 0.8;
        } catch (Exception e) {
            throw new RefundCalculationException("Could not calculate refund.");
        }
    }
}
