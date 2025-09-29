package com.airbook.util;

import org.springframework.stereotype.Component;

/**
 * Utility for payment gateway integration
 */
@Component
public class PaymentGatewayClient {
    /**
     * Charge a card using a payment gateway (mock implementation)
     * @param amount Amount to charge
     * @param cardNumber Card number
     * @param expiry Expiry date
     * @return true if payment successful
     */
    public boolean chargeCard(double amount, String cardNumber, String expiry) {
        // Integrate with Stripe/PayPal here
        // For demonstration, always return true
        return true;
    }
}
