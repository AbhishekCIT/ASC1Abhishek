package com.example.airbooking.model;

/**
 * Response model for payment processing.
 */
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;

    public PaymentResponse(String paymentStatus, String transactionId) {
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
    }
    public PaymentResponse() {}

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}
