package com.airtransport.model;

/**
 * Model representing a booking request payload.
 */
public class BookingRequest {
    private String flightId;
    private String origin;
    private String destination;
    private String date;
    private String userId;
    private PassengerDetails passengerDetails;
    private PaymentInfo paymentInfo;

    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public PassengerDetails getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(PassengerDetails passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
