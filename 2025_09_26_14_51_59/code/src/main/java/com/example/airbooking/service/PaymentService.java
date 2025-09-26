package com.example.airbooking.service;

import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import com.example.airbooking.util.PaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles payment processing for bookings.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Process payment for a booking.
     * Validates payment info and interacts with payment gateway.
     */
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Validate payment info (basic validation)
        if (paymentRequest == null || !paymentRequest.isValid()) {
            throw new IllegalArgumentException("Invalid payment information.");
        }
        // Call payment gateway
        PaymentResponse response = paymentGatewayClient.pay(paymentRequest);
        if (!"SUCCESS".equals(response.getPaymentStatus())) {
            throw new RuntimeException("Payment could not be processed.");
        }
        return response;
    }
}
