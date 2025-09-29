package com.example.airline.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private String bookingId;
    private BigDecimal amount;
    private String paymentMethod;
    // Add other fields as needed (e.g., card details, etc.)

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}