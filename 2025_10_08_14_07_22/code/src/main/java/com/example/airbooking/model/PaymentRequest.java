package com.example.airbooking.model;

/**
 * Request model for making a payment.
 */
public class PaymentRequest {
    private String bookingId;
    private BookingRequest.PaymentInfo paymentInfo;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public BookingRequest.PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(BookingRequest.PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
