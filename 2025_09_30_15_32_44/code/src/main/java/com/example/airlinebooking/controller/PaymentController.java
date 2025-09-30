package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.PaymentRequest;
import com.example.airlinebooking.model.PaymentResponse;
import com.example.airlinebooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing payment processing.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * Process a payment for a booking.
     * @param paymentRequest the payment request payload
     * @return payment response
     */
    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            PaymentResponse response = paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
