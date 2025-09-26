package com.example.airtransport.service;

import com.example.airtransport.model.Payment;
import com.example.airtransport.model.PaymentInfo;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service for integrating with payment providers (Stripe/PayPal).
 */
@Service
public class PaymentGatewayService {

    /**
     * Process payment using provided payment info.
     * This is a stub for actual payment gateway integration.
     */
    public Payment processPayment(PaymentInfo paymentInfo) {
        // TODO: Integrate with Stripe/PayPal
        // For demo, always succeed
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setMethod(paymentInfo.getMethod());
        payment.setAmount(paymentInfo.getAmount());
        payment.setStatus("SUCCESS");
        return payment;
    }
}
