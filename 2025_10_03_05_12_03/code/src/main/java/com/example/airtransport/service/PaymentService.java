package com.example.airtransport.service;

import com.example.airtransport.model.PaymentInfo;
import org.springframework.stereotype.Service;

/**
 * Service for payment processing with Payment Gateway API.
 */
@Service
public class PaymentService {
    /**
     * Processes payment using the provided payment info.
     * @param paymentInfo Payment information
     * @return true if payment is successful, false otherwise
     */
    public boolean processPayment(PaymentInfo paymentInfo) {
        // Simulate payment gateway integration (should call real API in production)
        // For demo, always return true if cardNumber is not empty
        return paymentInfo != null && paymentInfo.getCardNumber() != null && !paymentInfo.getCardNumber().isEmpty();
    }
}
