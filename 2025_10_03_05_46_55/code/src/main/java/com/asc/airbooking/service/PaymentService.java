package com.asc.airbooking.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Service for payment processing via Stripe.
 */
@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * Processes payment using Stripe API.
     * @param amount Amount to be charged
     * @param token Payment token
     * @return true if payment is successful, false otherwise
     */
    public boolean processPayment(double amount, String token) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100)) // Stripe expects amount in cents
                .setCurrency("usd")
                .setPaymentMethod(token)
                .setConfirm(true)
                .build();
            PaymentIntent intent = PaymentIntent.create(params);
            logger.info("Payment processed successfully: {}", intent.getId());
            return "succeeded".equals(intent.getStatus());
        } catch (StripeException e) {
            logger.error("Payment processing failed: {}", e.getMessage());
            return false;
        }
    }
}
