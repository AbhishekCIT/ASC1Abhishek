package com.example.airlinebooking.model;

/**
 * Response model for booking API.
 */
public class BookingResponse {
    private String bookingReference;
    private String status;

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
