package com.example.airbooking.model;

/**
 * Model for payment request payload.
 */
public class PaymentRequest {
    private Long bookingId;
    private double amount;
    private String method;

    // Getters and setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
