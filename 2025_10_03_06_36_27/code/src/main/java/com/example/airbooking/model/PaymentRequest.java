package com.example.airbooking.model;

/**
 * Request model for payment processing
 */
public class PaymentRequest {
    private String bookingId;
    private PaymentInfo paymentInfo;
    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
