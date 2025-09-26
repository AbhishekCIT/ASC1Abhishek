package com.example.airtransport.model;

/**
 * Model representing a booking request payload.
 */
public class BookingRequest {
    private String flightId;
    private PassengerInfo passengerInfo;
    private PaymentInfo paymentInfo;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public PassengerInfo getPassengerInfo() { return passengerInfo; }
    public void setPassengerInfo(PassengerInfo passengerInfo) { this.passengerInfo = passengerInfo; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
