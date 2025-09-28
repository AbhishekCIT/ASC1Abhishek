package com.example.airlinebooking.service;

import com.example.airlinebooking.model.BookingRequest;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * Simulated client for payment gateway integration.
 */
@Component
public class PaymentGatewayClient {
    /**
     * Initiates payment with the gateway (simulated).
     * @param paymentDetails payment details
     * @param amount amount to be charged
     * @return true if payment is successful, false otherwise
     */
    public boolean initiatePayment(BookingRequest.PaymentDetails paymentDetails, BigDecimal amount) {
        // Simulate payment processing logic
        // In real implementation, call Stripe/PayPal API
        return "4242424242424242".equals(paymentDetails.getCardNumber()) || "STRIPE".equalsIgnoreCase(paymentDetails.getPaymentMethod());
    }
}
