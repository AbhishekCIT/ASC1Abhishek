package com.example.airbooking.util;

import com.example.airbooking.model.PaymentInfo;
import org.springframework.stereotype.Component;

/**
 * Utility for payment processing (PCI DSS compliant integration point).
 */
@Component
public class PaymentGatewayService {
    /**
     * Processes payment securely (mock implementation).
     * @param paymentInfo Payment info
     * @param amount Amount to be charged
     * @return true if payment is successful, false otherwise
     */
    public boolean processPayment(PaymentInfo paymentInfo, double amount) {
        // TODO: Integrate with real payment gateway
        if (paymentInfo == null || paymentInfo.getCardNumber() == null || paymentInfo.getExpiry() == null) {
            return false;
        }
        // Assume payment always succeeds for demo
        return true;
    }
}
