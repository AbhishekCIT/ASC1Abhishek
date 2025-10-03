package com.airtransport.model;

/**
 * Model for payment request payload.
 */
public class PaymentRequest {
    private String bookingId;
    private PaymentInfo paymentInfo;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
