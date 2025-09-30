package com.example.airlinebooking.service;

import com.example.airlinebooking.model.PaymentInfo;
import com.example.airlinebooking.model.PaymentRequest;
import com.example.airlinebooking.model.PaymentResponse;
import com.example.airlinebooking.util.PaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for payment processing and gateway integration.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentGateway paymentGateway;

    /**
     * Process payment for a booking.
     * @param paymentInfo payment information
     * @param amount amount to be charged
     * @return true if payment is successful
     */
    public boolean processPayment(PaymentInfo paymentInfo, double amount) {
        return paymentGateway.charge(paymentInfo, amount);
    }

    /**
     * Process payment via API endpoint.
     * @param paymentRequest the payment request
     * @return payment response
     */
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        boolean success = paymentGateway.charge(paymentRequest.getDetails(), paymentRequest.getAmount());
        if (success) {
            return new PaymentResponse("P" + System.currentTimeMillis(), "SUCCESS");
        } else {
            throw new RuntimeException("Invalid card details");
        }
    }
}
