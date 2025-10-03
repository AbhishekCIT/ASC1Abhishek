package com.example.airtransport.controller;

import com.example.airtransport.model.PaymentInfo;
import com.example.airtransport.model.PaymentResponse;
import com.example.airtransport.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling payment processing.
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * Processes payment for a booking.
     * @param bookingId Booking ID
     * @param paymentInfo Payment information
     * @return PaymentResponse
     */
    @PostMapping("/process")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PaymentResponse> processPayment(@RequestParam String bookingId,
                                                         @Valid @RequestBody PaymentInfo paymentInfo) {
        boolean success = paymentService.processPayment(bookingId, paymentInfo);
        PaymentResponse response = PaymentResponse.builder()
                .paymentStatus(success ? "SUCCESS" : "FAILED")
                .transactionId(success ? "T" + System.currentTimeMillis() : null)
                .build();
        return ResponseEntity.ok(response);
    }
}
