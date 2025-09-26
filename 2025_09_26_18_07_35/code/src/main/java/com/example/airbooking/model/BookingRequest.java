package com.example.airbooking.model;

/**
 * Model for booking request.
 */
public class BookingRequest {
    private String flightId;
    private String userId;
    private Object passengerInfo; // This can be refined to a specific class
    private PaymentInfo paymentInfo;

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Object getPassengerInfo() { return passengerInfo; }
    public void setPassengerInfo(Object passengerInfo) { this.passengerInfo = passengerInfo; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
