package com.example.airlinebooking.util;

import com.example.airlinebooking.model.PaymentInfo;
import org.springframework.stereotype.Component;

/**
 * Utility for payment gateway integration (PCI DSS compliant).
 */
@Component
public class PaymentGateway {
    /**
     * Simulates charging a payment method.
     * @param paymentInfo payment details
     * @param amount amount to charge
     * @return true if payment is successful
     */
    public boolean charge(PaymentInfo paymentInfo, double amount) {
        // In real implementation, integrate with payment gateway
        if (paymentInfo != null && paymentInfo.getCardNumber() != null && paymentInfo.getCardNumber().startsWith("4")) {
            return true; // Simulate VISA success
        }
        return false;
    }
}
