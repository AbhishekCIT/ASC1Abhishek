package com.example.flightbooking.service;

import com.example.flightbooking.model.PaymentInfo;
import com.example.flightbooking.util.PaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling payment authorization and processing.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Authorizes payment for a booking.
     * @param paymentInfo payment information
     * @param amount amount to be charged
     * @return true if payment is authorized, false otherwise
     */
    public boolean authorizePayment(PaymentInfo paymentInfo, double amount) {
        return paymentGatewayClient.processPayment(paymentInfo, amount);
    }
}
