package com.example.airbooking.service;

import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import com.example.airbooking.util.PaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling payment processing.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Process a payment through the payment gateway.
     */
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Validate payment method and funds
        if (paymentRequest.getAmount() <= 0 || paymentRequest.getMethod() == null) {
            throw new PaymentException("Invalid payment info");
        }
        return paymentGatewayClient.pay(paymentRequest);
    }
}
