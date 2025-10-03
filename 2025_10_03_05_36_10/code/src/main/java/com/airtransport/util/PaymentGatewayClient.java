package com.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * Simulates integration with external payment gateway
 */
@Component
public class PaymentGatewayClient {
    /**
     * Simulate payment gateway call
     * @param paymentDetails Payment details
     * @param amount Payment amount
     * @return true if payment successful, false otherwise
     */
    public boolean callPaymentGateway(Map<String, String> paymentDetails, Double amount) {
        // Simulate payment processing logic
        // In real implementation, integrate with payment gateway API
        if (paymentDetails.get("cardNumber") != null && amount > 0) {
            return true; // Assume payment is successful for valid card and positive amount
        }
        return false;
    }
}
