package com.airtransport.booking.controller;

import com.airtransport.booking.entity.Payment;
import com.airtransport.booking.model.PaymentRequest;
import com.airtransport.booking.model.PaymentResponse;
import com.airtransport.booking.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for payment processing APIs.
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * Process payment for a booking.
     */
    @PostMapping("/process")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(request);
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus(payment.getStatus());
        response.setTransactionId(payment.getId());
        return ResponseEntity.ok(response);
    }
}
