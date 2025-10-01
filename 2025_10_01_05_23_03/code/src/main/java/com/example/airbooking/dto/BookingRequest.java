package com.example.airbooking.dto;

import java.util.Map;

/**
 * DTO for booking request.
 */
public class BookingRequest {
    private Integer flightId;
    private Map<String, Object> passengerInfo; // Can be replaced with a dedicated DTO if needed
    private Map<String, Object> paymentInfo;   // Can be replaced with a dedicated DTO if needed

    // Getters and Setters
    public Integer getFlightId() { return flightId; }
    public void setFlightId(Integer flightId) { this.flightId = flightId; }
    public Map<String, Object> getPassengerInfo() { return passengerInfo; }
    public void setPassengerInfo(Map<String, Object> passengerInfo) { this.passengerInfo = passengerInfo; }
    public Map<String, Object> getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(Map<String, Object> paymentInfo) { this.paymentInfo = paymentInfo; }
}
