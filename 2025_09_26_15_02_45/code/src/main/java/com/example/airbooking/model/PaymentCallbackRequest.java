package com.example.airbooking.model;

/**
 * Model for payment callback request
 */
public class PaymentCallbackRequest {
    private String transactionId;
    private String status;
    private String bookingRef;

    // Getters and Setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
}
