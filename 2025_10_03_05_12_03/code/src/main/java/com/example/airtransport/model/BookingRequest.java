package com.example.airtransport.model;

import java.util.List;

/**
 * Model representing booking request for booking API.
 */
public class BookingRequest {
    private int flightId;
    private List<Passenger> passengers;
    private List<String> seats;
    private PaymentInfo paymentInfo;

    // Getters and setters
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
    public List<String> getSeats() { return seats; }
    public void setSeats(List<String> seats) { this.seats = seats; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
