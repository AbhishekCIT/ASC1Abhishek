package com.example.airlinebooking.service;

import com.example.airlinebooking.model.Payment;
import com.example.airlinebooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for handling payment processing with Stripe integration.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Charges the payment using Stripe (mocked for demo).
     * @param amount the amount to charge
     * @param paymentInfo the payment information (card details, etc.)
     * @return Payment entity with status
     * @throws RuntimeException if payment fails
     */
    public Payment charge(BigDecimal amount, Object paymentInfo) {
        // Here, integrate with Stripe API. For demo, assume always successful.
        boolean paymentSuccess = true; // Replace with real Stripe API call
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setAmount(amount);
        payment.setStatus(paymentSuccess ? "SUCCESS" : "FAILED");
        payment.setPaymentDate(LocalDateTime.now());
        if (!paymentSuccess) {
            throw new RuntimeException("Payment failed");
        }
        return paymentRepository.save(payment);
    }
}
