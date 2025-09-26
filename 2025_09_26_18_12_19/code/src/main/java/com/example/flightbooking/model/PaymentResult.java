package com.example.flightbooking.model;

/**
 * Model for payment processing result.
 */
public class PaymentResult {
    private boolean success;
    private String transactionId;
    private String status;

    public PaymentResult() {}
    public PaymentResult(boolean success, String transactionId, String status) {
        this.success = success;
        this.transactionId = transactionId;
        this.status = status;
    }

    // Getters and setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
