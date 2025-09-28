package com.airtransport.booking.controller;

import com.airtransport.booking.entity.PaymentInfo;
import com.airtransport.booking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for payment processing APIs.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * Process a payment.
     * @param paymentInfo Payment info
     * @return Payment status and transaction ID
     */
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody PaymentInfo paymentInfo) {
        PaymentInfo result = paymentService.processPayment(paymentInfo);
        Map<String, Object> response = new HashMap<>();
        response.put("paymentStatus", result.getStatus());
        response.put("transactionId", result.getTransactionId());
        return ResponseEntity.ok(response);
    }
}
