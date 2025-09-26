package com.example.airtransport.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model representing a booking.
 */
public class Booking {
    private String bookingReference;
    private String flightId;
    private List<PassengerDetail> passengerDetails;
    private String status;
    private LocalDateTime bookingTime;

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public List<PassengerDetail> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetail> passengerDetails) { this.passengerDetails = passengerDetails; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
}
