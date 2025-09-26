package com.example.flightbooking.model;

import java.util.Map;

/**
 * Request model for booking a flight.
 */
public class BookingRequest {
    private String flightId;
    private String userId;
    private Map<String, Object> passengerDetails;
    private PaymentDetails paymentDetails;

    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Map<String, Object> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(Map<String, Object> passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentDetails getPaymentDetails() { return paymentDetails; }
    public void setPaymentDetails(PaymentDetails paymentDetails) { this.paymentDetails = paymentDetails; }
}
