package com.example.airtransport.model;

/**
 * Model representing a booking request payload.
 */
public class BookingRequest {
    private String flightId;
    private String userId;
    private PaymentInfo paymentInfo;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
