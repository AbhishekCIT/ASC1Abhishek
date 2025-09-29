package com.airbook.model;

import java.time.LocalDateTime;

/**
 * Model for Booking entity
 */
public class Booking {
    private String bookingId;
    private String flightId;
    private String passengerName;
    private String passengerEmail;
    private String status;
    private String eTicket;
    private LocalDateTime createdAt;

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public String getPassengerEmail() { return passengerEmail; }
    public void setPassengerEmail(String passengerEmail) { this.passengerEmail = passengerEmail; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getETicket() { return eTicket; }
    public void setETicket(String eTicket) { this.eTicket = eTicket; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
