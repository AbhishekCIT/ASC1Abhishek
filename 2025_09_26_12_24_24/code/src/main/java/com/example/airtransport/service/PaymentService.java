package com.example.airtransport.service;

import com.example.airtransport.model.PaymentRequest;
import com.example.airtransport.model.PaymentResponse;
import com.example.airtransport.util.ValidationUtil;
import com.example.airtransport.integration.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for payment processing logic.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Process payment for a booking.
     * @param paymentRequest Payment request payload
     * @return Payment response
     */
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Validate payment info
        validationUtil.validatePaymentInfo(paymentRequest.getPaymentInfo());
        // Call payment gateway
        boolean paymentStatus = paymentGatewayService.initiatePayment(paymentRequest.getPaymentInfo(), paymentRequest.getAmount());
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId("P" + System.currentTimeMillis());
        response.setStatus(paymentStatus ? "SUCCESS" : "FAILED");
        response.setTimestamp(LocalDateTime.now().toString());
        return response;
    }
}
