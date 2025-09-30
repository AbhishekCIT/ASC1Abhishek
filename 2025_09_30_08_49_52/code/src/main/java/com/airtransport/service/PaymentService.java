package com.airtransport.service;

import com.airtransport.model.BookFlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service for payment processing.
 */
@Service
public class PaymentService {
    private final PaymentGatewayClient paymentGatewayClient;

    @Autowired
    public PaymentService(PaymentGatewayClient paymentGatewayClient) {
        this.paymentGatewayClient = paymentGatewayClient;
    }

    /**
     * Process payment using payment details and amount.
     * @param paymentDetails payment details from request
     * @param amount amount to be charged
     * @return true if payment is successful, false otherwise
     */
    public boolean processPayment(BookFlightRequest.PaymentDetails paymentDetails, BigDecimal amount) {
        // Call the payment gateway client
        return paymentGatewayClient.authorize(paymentDetails, amount);
    }
}
