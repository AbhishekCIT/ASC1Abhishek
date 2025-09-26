package com.example.airbooking.service;

import com.example.airbooking.model.PaymentInfo;
import com.example.airbooking.model.PaymentResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for processing payments securely.
 */
@Service
public class PaymentService {
    /**
     * Processes payment using payment gateway.
     * @param info PaymentInfo
     * @return PaymentResult
     */
    public PaymentResult processPayment(PaymentInfo info) {
        // TODO: Integrate with payment gateway. Here, return mock result.
        PaymentResult result = new PaymentResult();
        if (info.getCardNumber() == null || info.getCvv() == null) {
            result.setSuccess(false);
            result.setErrorMessage("Invalid payment information");
            result.setStatus("FAILED");
        } else {
            result.setSuccess(true);
            result.setPaymentId(UUID.randomUUID().toString());
            result.setStatus("CONFIRMED");
        }
        return result;
    }
}
