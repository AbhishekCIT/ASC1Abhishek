package com.example.airbooking.util;

import org.springframework.stereotype.Component;

/**
 * Utility for payment gateway integration
 */
@Component
public class PaymentService {
    /**
     * Process payment with payment gateway
     * @param paymentInfo payment information
     * @return true if payment successful
     */
    public boolean processPayment(Object paymentInfo) {
        // Integrate with payment gateway API here
        // For demo, always return true
        return true;
    }
}
