package com.example.airbooking.util;

import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import org.springframework.stereotype.Component;

/**
 * Utility class for integrating with payment gateways (Stripe/PayPal).
 * (Stub implementation; replace with actual payment gateway integration.)
 */
@Component
public class PaymentGatewayClient {

    public PaymentResponse pay(PaymentRequest paymentRequest) {
        // TODO: Integrate with Stripe/PayPal
        if ("Stripe".equalsIgnoreCase(paymentRequest.getMethod()) || "PayPal".equalsIgnoreCase(paymentRequest.getMethod())) {
            return new PaymentResponse(456L, "SUCCESS");
        }
        return new PaymentResponse(null, "FAILED");
    }
}
