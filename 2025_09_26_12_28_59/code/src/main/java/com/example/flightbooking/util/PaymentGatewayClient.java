package com.example.flightbooking.util;

import com.example.flightbooking.model.PaymentInfo;
import org.springframework.stereotype.Component;

/**
 * Utility for integrating with external payment gateway.
 */
@Component
public class PaymentGatewayClient {

    /**
     * Processes payment with the payment gateway.
     * @param paymentInfo payment information
     * @param amount amount to be charged
     * @return true if payment is authorized, false otherwise
     */
    public boolean processPayment(PaymentInfo paymentInfo, double amount) {
        // TODO: Integrate with real payment gateway
        // For now, always return true (simulate successful payment)
        return true;
    }
}
