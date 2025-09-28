package com.example.airlinebooking.model;

/**
 * Request model for payment callback API.
 */
public class PaymentCallbackRequest {
    private String transactionId;
    private String status;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
