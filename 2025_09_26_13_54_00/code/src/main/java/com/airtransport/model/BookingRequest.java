package com.airtransport.model;

/**
 * BookingRequest represents a booking request payload.
 */
public class BookingRequest {
    private Long flightId;
    private Long userId;
    private String seatPreference;
    private PaymentInfo paymentInfo;

    public BookingRequest() {}
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getSeatPreference() { return seatPreference; }
    public void setSeatPreference(String seatPreference) { this.seatPreference = seatPreference; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }
}
