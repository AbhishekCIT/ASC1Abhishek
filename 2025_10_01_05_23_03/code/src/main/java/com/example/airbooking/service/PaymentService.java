package com.example.airbooking.service;

import com.example.airbooking.entity.Payment;
import com.example.airbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Service for handling payment processing and integration (e.g., Stripe).
 * This is a stub implementation for demonstration.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Processes a payment and returns the Payment entity.
     * @param paymentInfo Payment info map (e.g., card details)
     * @param amount Amount to be charged
     * @return Payment entity with status
     */
    public Payment processPayment(Map<String, Object> paymentInfo, BigDecimal amount) {
        // Simulate payment gateway integration
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setStatus("CONFIRMED"); // Assume always successful for demo
        payment.setPaidAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}
