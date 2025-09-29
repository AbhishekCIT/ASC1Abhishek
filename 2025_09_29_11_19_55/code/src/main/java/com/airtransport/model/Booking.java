package com.airtransport.model;

import java.time.LocalDate;

/**
 * Model representing a booking.
 */
public class Booking {
    private String bookingId;
    private String userId;
    private String flightId;
    private String status;
    private LocalDate bookingDate;
    private boolean confirmationEmailSent;

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public boolean isConfirmationEmailSent() { return confirmationEmailSent; }
    public void setConfirmationEmailSent(boolean confirmationEmailSent) { this.confirmationEmailSent = confirmationEmailSent; }
}
