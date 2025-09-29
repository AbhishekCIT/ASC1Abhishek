package com.example.airbooking.model;

/**
 * Model for booking request payload.
 */
public class BookingRequest {
    private Long flightId;
    private Passenger passengerDetails;
    private String seat;
    private PaymentInfo paymentInfo;

    // Getters and Setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Passenger getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(Passenger passengerDetails) { this.passengerDetails = passengerDetails; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
