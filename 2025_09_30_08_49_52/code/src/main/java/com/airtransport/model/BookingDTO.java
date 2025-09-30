package com.airtransport.model;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Booking.
 */
public class BookingDTO {
    private String bookingId;
    private FlightDTO flight;
    private PassengerDTO passenger;
    private String status;
    private LocalDateTime createdAt;

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public FlightDTO getFlight() { return flight; }
    public void setFlight(FlightDTO flight) { this.flight = flight; }
    public PassengerDTO getPassenger() { return passenger; }
    public void setPassenger(PassengerDTO passenger) { this.passenger = passenger; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
