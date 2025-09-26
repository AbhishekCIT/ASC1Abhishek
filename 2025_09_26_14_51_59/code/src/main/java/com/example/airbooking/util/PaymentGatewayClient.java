package com.example.airbooking.util;

import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import org.springframework.stereotype.Component;

/**
 * Utility class to integrate with payment gateway for payment processing.
 */
@Component
public class PaymentGatewayClient {
    /**
     * Process payment via payment gateway. (Stub implementation)
     */
    public PaymentResponse pay(PaymentRequest paymentRequest) {
        // TODO: Integrate with actual payment gateway
        // Stub: Simulate payment success
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus("SUCCESS");
        response.setTransactionId("TXN" + System.currentTimeMillis());
        return response;
    }
}
