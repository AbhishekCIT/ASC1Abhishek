package com.airtransport.controller;

import com.airtransport.model.Payment;
import com.airtransport.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for handling payment processing requests.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Process a payment for a booking.
     * @param paymentRequest Payment request payload
     * @return Payment status or error
     */
    @PostMapping("/process")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Payment payment = paymentService.processPayment(paymentRequest.getBookingId(), paymentRequest.getPaymentDetails());
            if ("SUCCESS".equals(payment.getStatus())) {
                return ResponseEntity.ok().body(new PaymentResponse(payment.getStatus(), payment.getTransactionId()));
            } else {
                return ResponseEntity.badRequest().body(new ErrorResponse("Payment failed."));
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal server error."));
        }
    }

    // Payment request DTO
    public static class PaymentRequest {
        private String bookingId;
        private Map<String, Object> paymentDetails;
        // Getters and setters
        public String getBookingId() { return bookingId; }
        public void setBookingId(String bookingId) { this.bookingId = bookingId; }
        public Map<String, Object> getPaymentDetails() { return paymentDetails; }
        public void setPaymentDetails(Map<String, Object> paymentDetails) { this.paymentDetails = paymentDetails; }
    }
    // Payment response DTO
    public static class PaymentResponse {
        private String paymentStatus;
        private String transactionId;
        public PaymentResponse(String paymentStatus, String transactionId) {
            this.paymentStatus = paymentStatus;
            this.transactionId = transactionId;
        }
        public String getPaymentStatus() { return paymentStatus; }
        public String getTransactionId() { return transactionId; }
    }
    // Error response DTO
    public static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
