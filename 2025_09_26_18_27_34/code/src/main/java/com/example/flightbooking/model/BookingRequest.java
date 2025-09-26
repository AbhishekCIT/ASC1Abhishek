package com.example.flightbooking.model;

import java.util.List;

/**
 * Model for booking request.
 */
public class BookingRequest {
    private Long flightId;
    private List<PassengerDetail> passengerDetails;
    private PaymentInfo paymentInfo;

    // Getters and Setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public List<PassengerDetail> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
