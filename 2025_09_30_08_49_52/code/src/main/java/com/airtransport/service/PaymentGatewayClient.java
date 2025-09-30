package com.airtransport.service;

import com.airtransport.model.BookFlightRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Mocked Payment Gateway Client for payment authorization.
 */
@Component
public class PaymentGatewayClient {
    /**
     * Authorize payment with payment details and amount.
     * In a real implementation, this would call Stripe or another gateway.
     * @param paymentDetails payment details
     * @param amount amount to charge
     * @return true if authorized, false otherwise
     */
    public boolean authorize(BookFlightRequest.PaymentDetails paymentDetails, BigDecimal amount) {
        // Mocked logic: accept any card number not ending with '0'
        return paymentDetails.getCardNumber() != null && !paymentDetails.getCardNumber().endsWith("0");
    }
}
