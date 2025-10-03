package com.example.airbooking.service;

import com.example.airbooking.model.Payment;
import com.example.airbooking.model.PaymentInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service for handling payment authorization and processing.
 */
@Service
public class PaymentGatewayService {
    /**
     * Process payment and return payment status.
     * This is a stub for integration with a real payment gateway.
     */
    public Payment processPayment(PaymentInfo paymentInfo) {
        Payment payment = new Payment();
        payment.setAmount(paymentInfo.getAmount());
        payment.setPaymentMethod(paymentInfo.getPaymentMethod());
        payment.setPaymentDate(LocalDateTime.now());
        // Simulate payment processing
        if (paymentInfo.getCardNumber() != null && paymentInfo.getCardNumber().length() == 16) {
            payment.setPaymentStatus("SUCCESS");
        } else {
            payment.setPaymentStatus("FAILED");
        }
        return payment;
    }
}
