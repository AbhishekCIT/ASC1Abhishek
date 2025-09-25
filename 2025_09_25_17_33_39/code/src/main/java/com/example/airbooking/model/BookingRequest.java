package com.example.airbooking.model;

import java.util.List;

/**
 * BookingRequest represents the payload for booking a flight.
 */
public class BookingRequest {
    private Long flightId;
    private Long userId;
    private List<PassengerDetail> passengerDetails;
    private PaymentInfo paymentInfo;

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<PassengerDetail> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
