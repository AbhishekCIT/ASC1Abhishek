package com.example.booking.client;

import com.example.booking.model.PaymentInfo;
import com.example.booking.exception.PaymentFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Simulates integration with a payment gateway (Stripe/PayPal).
 */
@Component
public class PaymentGatewayClient {
    private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayClient.class);

    /**
     * Processes payment and returns true if successful.
     * @param paymentInfo Payment info
     * @return true if payment authorized
     */
    public boolean processPayment(PaymentInfo paymentInfo) {
        logger.debug("Processing payment: {}", paymentInfo);
        // Simulate payment: fail if token is 'fail', succeed otherwise
        if (paymentInfo == null || paymentInfo.getToken() == null || paymentInfo.getToken().equals("fail")) {
            logger.error("Payment failed for token: {}", paymentInfo != null ? paymentInfo.getToken() : null);
            return false;
        }
        logger.info("Payment successful for token: {}", paymentInfo.getToken());
        return true;
    }
}
