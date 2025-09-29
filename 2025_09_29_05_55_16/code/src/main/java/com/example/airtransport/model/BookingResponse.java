package com.example.airtransport.model;

/**
 * Model representing a booking response payload.
 */
public class BookingResponse {
    private String bookingId;
    private String status;
    private Booking details;

    public BookingResponse(String bookingId, String status, Booking details) {
        this.bookingId = bookingId;
        this.status = status;
        this.details = details;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Booking getDetails() { return details; }
    public void setDetails(Booking details) { this.details = details; }
}
