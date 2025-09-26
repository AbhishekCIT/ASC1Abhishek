package com.example.flightbooking.model;

/**
 * Model for payment result/response.
 */
public class PaymentResult {
    private String paymentStatus;
    private String transactionId;

    // Getters and Setters
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}
