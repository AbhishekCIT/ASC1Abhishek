package com.example.airbooking.service;

import com.example.airbooking.entity.Payment;
import com.example.airbooking.exception.PaymentFailedException;
import org.springframework.stereotype.Service;

/**
 * Service for handling payment processing.
 * For demo, this simulates payment success.
 */
@Service
public class PaymentService {
    /**
     * Processes payment. Throws PaymentFailedException if payment fails.
     */
    public Payment processPayment(Payment paymentInfo) {
        // Simulate payment gateway integration (e.g., Stripe)
        if (paymentInfo.getAmount() == null || paymentInfo.getAmount().doubleValue() <= 0) {
            throw new PaymentFailedException("Payment amount invalid");
        }
        paymentInfo.setStatus("SUCCESS");
        paymentInfo.setPaymentDate(java.time.LocalDateTime.now());
        return paymentInfo;
    }
}
