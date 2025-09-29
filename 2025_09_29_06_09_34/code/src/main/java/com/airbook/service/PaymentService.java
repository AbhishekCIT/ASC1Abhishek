package com.airbook.service;

import com.airbook.model.Payment;
import com.airbook.repository.PaymentRepository;
import com.airbook.util.PaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for payment processing and validation
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Process payment for a booking
     * @param payment Payment request
     * @return Payment status
     */
    public Payment processPayment(Payment payment) {
        // Validate payment details
        if (!isValidCard(payment.getCardNumber(), payment.getExpiry())) {
            throw new IllegalArgumentException("Invalid payment details");
        }
        // Integrate with payment gateway
        boolean success = paymentGatewayClient.chargeCard(payment.getAmount(), payment.getCardNumber(), payment.getExpiry());
        if (!success) {
            throw new IllegalArgumentException("Payment gateway failure");
        }
        // Save payment
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setStatus("SUCCESS");
        payment.setTransactionId("T" + UUID.randomUUID().toString().substring(0, 8));
        payment.setPaidAt(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment;
    }

    /**
     * Validate card number and expiry
     */
    private boolean isValidCard(String cardNumber, String expiry) {
        // Simple validation logic (expand as needed)
        return cardNumber != null && cardNumber.length() >= 12 && expiry != null && expiry.matches("\\d{2}/\\d{2}");
    }
}
