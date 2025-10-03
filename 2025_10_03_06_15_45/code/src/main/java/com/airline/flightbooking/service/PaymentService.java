package com.airline.flightbooking.service;

import com.airline.flightbooking.exception.PaymentFailedException;
import com.airline.flightbooking.model.Booking;
import com.airline.flightbooking.model.Payment;
import com.airline.flightbooking.repository.BookingRepository;
import com.airline.flightbooking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service for payment processing.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final AuditLogService auditLogService;

    /**
     * Process payment for a booking.
     * @param bookingId booking to pay for
     * @param amount payment amount
     * @param paymentMethod payment method (e.g., CARD)
     * @return Payment entity
     * @throws PaymentFailedException if payment fails
     */
    @Transactional
    public Payment processPayment(String bookingId, double amount, String paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new PaymentFailedException("Booking not found for payment"));
        // Simulate payment gateway integration
        boolean paymentSuccess = true; // Replace with real gateway logic
        Payment payment = Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .booking(booking)
                .amount(amount)
                .status(paymentSuccess ? "SUCCESS" : "FAILED")
                .build();
        paymentRepository.save(payment);
        if (!paymentSuccess) {
            auditLogService.logEvent("PAYMENT_FAILED", "Payment failed for booking: " + bookingId);
            throw new PaymentFailedException("Payment processing failed");
        }
        auditLogService.logEvent("PAYMENT_SUCCESS", "Payment successful for booking: " + bookingId);
        return payment;
    }
}
