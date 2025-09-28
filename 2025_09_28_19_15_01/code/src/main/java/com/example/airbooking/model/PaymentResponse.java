package com.example.airbooking.model;

/**
 * Response model for payment processing.
 */
public class PaymentResponse {
    private String paymentId;
    private String status;

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
