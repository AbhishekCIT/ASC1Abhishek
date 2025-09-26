package com.example.airtransport.model;

/**
 * Model representing a payment response payload.
 */
public class PaymentResponse {
    private String paymentId;
    private String status;
    private String timestamp;

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
