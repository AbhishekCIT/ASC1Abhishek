package com.example.airbooking.model;

/**
 * Request model for booking a flight
 */
public class BookingRequest {
    private String flightId;
    private PassengerInfo passengerInfo;
    private PaymentInfo paymentInfo;
    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public PassengerInfo getPassengerInfo() { return passengerInfo; }
    public void setPassengerInfo(PassengerInfo passengerInfo) { this.passengerInfo = passengerInfo; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
