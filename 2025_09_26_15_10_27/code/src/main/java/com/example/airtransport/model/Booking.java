package com.example.airtransport.model;

import java.time.LocalDateTime;

/**
 * Model representing a Booking.
 */
public class Booking {
    private String bookingRef;
    private String flightId;
    private PassengerInfo passengerInfo;
    private String status;
    private LocalDateTime bookingDate;
    private double totalPrice;
    private Payment payment;

    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public PassengerInfo getPassengerInfo() { return passengerInfo; }
    public void setPassengerInfo(PassengerInfo passengerInfo) { this.passengerInfo = passengerInfo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
}
