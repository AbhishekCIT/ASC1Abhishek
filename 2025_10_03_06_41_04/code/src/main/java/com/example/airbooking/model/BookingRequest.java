package com.example.airbooking.model;

import java.util.List;

/**
 * DTO for booking requests.
 */
public class BookingRequest {
    private Long flightId;
    private List<Passenger> passengerDetails;
    private PaymentInfo paymentInfo;

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public List<Passenger> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<Passenger> passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
