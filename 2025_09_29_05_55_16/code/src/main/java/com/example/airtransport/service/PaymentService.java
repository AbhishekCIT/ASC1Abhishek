package com.example.airtransport.service;

import com.example.airtransport.model.PaymentInfo;
import com.example.airtransport.model.PaymentResult;
import com.example.airtransport.exception.PaymentProcessingException;
import org.springframework.stereotype.Service;

/**
 * Service for processing payments securely (PCI DSS compliant).
 */
@Service
public class PaymentService {

    /**
     * Process payment for a booking.
     * @param paymentInfo Payment information.
     * @return PaymentResult indicating success or failure.
     * @throws PaymentProcessingException if payment fails.
     */
    public PaymentResult processPayment(PaymentInfo paymentInfo) throws PaymentProcessingException {
        // TODO: Integrate with payment gateway
        // Stub: Assume payment is always successful if card number is not empty
        if (paymentInfo == null || paymentInfo.getCardNumber() == null || paymentInfo.getCardNumber().isEmpty()) {
            throw new PaymentProcessingException("Invalid payment info");
        }
        return new PaymentResult(true, "Payment successful");
    }
}
