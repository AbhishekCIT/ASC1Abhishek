package com.example.airbooking.model;

/**
 * BookingConfirmation represents the response after booking a flight.
 */
public class BookingConfirmation {
    private Long bookingId;
    private String status;
    private Booking details;

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Booking getDetails() { return details; }
    public void setDetails(Booking details) { this.details = details; }
}
