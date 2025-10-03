package com.airtransport.model;

/**
 * Model for booking request payload.
 */
public class BookingRequest {
    private String flightId;
    private String userId;
    private Object passengerDetails; // Can be replaced with a proper PassengerDetails model
    private PaymentInfo paymentInfo;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Object getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(Object passengerDetails) { this.passengerDetails = passengerDetails; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
