package com.example.airlinebooking.model;

/**
 * Response payload for payment processing.
 */
public class PaymentResponse {
    private String paymentId;
    private String status;

    public PaymentResponse() {}
    public PaymentResponse(String paymentId, String status) {
        this.paymentId = paymentId;
        this.status = status;
    }
    // Getters and setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
