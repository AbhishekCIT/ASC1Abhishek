package com.example.airlinebooking.model;

/**
 * Request payload for booking a flight.
 */
public class BookingRequest {
    private Integer flightId;
    private Integer userId;
    private PaymentInfo paymentInfo;

    // Getters and setters
    public Integer getFlightId() { return flightId; }
    public void setFlightId(Integer flightId) { this.flightId = flightId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
