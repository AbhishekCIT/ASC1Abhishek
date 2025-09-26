package com.example.airtransport.model;

import java.util.List;

/**
 * Model representing a booking request payload.
 */
public class BookingRequest {
    private String flightId;
    private List<PassengerDetail> passengerDetails;
    private Object paymentInfo;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public List<PassengerDetail> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) { this.passengerDetails = passengerDetails; }
    public Object getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(Object paymentInfo) { this.paymentInfo = paymentInfo; }
}
