package com.airtransport.model;

/**
 * Model for email notification request payload.
 */
public class EmailRequest {
    private String bookingId;
    private String email;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
