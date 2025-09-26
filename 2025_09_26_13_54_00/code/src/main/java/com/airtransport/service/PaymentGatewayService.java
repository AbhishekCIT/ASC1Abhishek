package com.airtransport.service;

import com.airtransport.model.PaymentInfo;
import org.springframework.stereotype.Service;

/**
 * PaymentGatewayService integrates with external payment gateway for processing payments.
 */
@Service
public class PaymentGatewayService {
    /**
     * Processes payment using payment information.
     * Returns true if payment is successful, false otherwise.
     */
    public boolean processPayment(PaymentInfo paymentInfo) {
        // TODO: Integrate with real payment gateway
        // For demo, accept all payments with valid card number
        return paymentInfo != null && paymentInfo.getCardNumber() != null && paymentInfo.getCardNumber().length() == 16;
    }
}
