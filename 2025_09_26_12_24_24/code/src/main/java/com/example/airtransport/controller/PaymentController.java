package com.example.airtransport.controller;

import com.example.airtransport.model.PaymentRequest;
import com.example.airtransport.model.PaymentResponse;
import com.example.airtransport.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling payment processing endpoints.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * Process payment for a booking.
     * @param paymentRequest Payment request payload
     * @return Payment response
     */
    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(response);
    }
}
