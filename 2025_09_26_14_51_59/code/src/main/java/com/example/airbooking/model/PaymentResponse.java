package com.example.airbooking.model;

/**
 * Model for payment response payload.
 */
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}
