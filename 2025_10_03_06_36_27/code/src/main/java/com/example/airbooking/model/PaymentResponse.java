package com.example.airbooking.model;

/**
 * Response model for payment processing
 */
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;
    // Getters and setters
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}
