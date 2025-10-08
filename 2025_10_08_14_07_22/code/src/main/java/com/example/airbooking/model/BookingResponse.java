package com.example.airbooking.model;

/**
 * Response model for booking a flight.
 */
public class BookingResponse {
    private String bookingId;
    private String status;

    public BookingResponse(String bookingId, String status) {
        this.bookingId = bookingId;
        this.status = status;
    }
    public BookingResponse() {}

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
