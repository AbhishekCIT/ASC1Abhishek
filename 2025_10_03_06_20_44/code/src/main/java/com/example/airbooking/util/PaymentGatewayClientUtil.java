package com.example.airbooking.util;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * Utility for integrating with payment gateways (Stripe, PayPal).
 * This is a stub for demonstration; in production, real API calls would be made.
 */
@Component
public class PaymentGatewayClientUtil {
    /**
     * Process payment via payment gateway.
     */
    public boolean pay(BigDecimal amount, String paymentMethod, String cardNumber, String cardHolder, String expiry, String cvv) {
        // TODO: Integrate with real payment gateway APIs
        return true;
    }
}
