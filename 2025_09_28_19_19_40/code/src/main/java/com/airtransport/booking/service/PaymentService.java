package com.airtransport.booking.service;

import com.airtransport.booking.entity.PaymentInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for processing payments securely.
 */
@Service
public class PaymentService {
    /**
     * Processes a payment and returns the result.
     * @param paymentInfo Payment information
     * @return PaymentInfo with updated status and transactionId
     */
    public PaymentInfo processPayment(PaymentInfo paymentInfo) {
        // Validate payment details
        if (paymentInfo == null || paymentInfo.getAmount() == null || paymentInfo.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid payment details");
        }
        // TODO: Integrate with payment gateway API
        // Simulate payment processing
        paymentInfo.setStatus("SUCCESS");
        paymentInfo.setTransactionId(UUID.randomUUID().toString());
        return paymentInfo;
    }
}
