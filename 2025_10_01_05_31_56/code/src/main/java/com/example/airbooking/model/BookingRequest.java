package com.example.airbooking.model;

/**
 * Model for booking request payload.
 */
public class BookingRequest {
    private Long flightId;
    private PassengerDetails passengerDetails;
    private PaymentRequest paymentInfo;

    // Getters and setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public PassengerDetails getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(PassengerDetails passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentRequest getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentRequest paymentInfo) { this.paymentInfo = paymentInfo; }
}
