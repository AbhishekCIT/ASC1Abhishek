package com.example.airbooking.service;

import com.example.airbooking.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for payment processing.
 */
@Service
public class PaymentService {
    /**
     * Processes payment for a booking.
     * @param bookingId Booking ID
     * @param paymentInfo Payment information
     * @return Processed payment
     */
    public Payment processPayment(String bookingId, Payment paymentInfo) {
        // Validate payment info (dummy validation for example)
        if (paymentInfo.getAmount() <= 0 || paymentInfo.getStatus() == null) {
            throw new IllegalArgumentException("Invalid payment information.");
        }
        // Simulate payment gateway integration
        paymentInfo.setPaymentId(UUID.randomUUID().toString());
        paymentInfo.setBookingId(bookingId);
        paymentInfo.setStatus("SUCCESS");
        paymentInfo.setPaymentDate(LocalDateTime.now());
        // Log payment attempt (could use logger)
        // Return processed payment
        return paymentInfo;
    }
}