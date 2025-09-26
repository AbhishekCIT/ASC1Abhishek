package com.example.airtransport.model;

/**
 * Model representing a payment request payload.
 */
public class PaymentRequest {
    private String bookingReference;
    private Object paymentInfo;
    private double amount;

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public Object getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(Object paymentInfo) { this.paymentInfo = paymentInfo; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
