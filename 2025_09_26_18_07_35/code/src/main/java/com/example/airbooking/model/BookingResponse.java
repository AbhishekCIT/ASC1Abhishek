package com.example.airbooking.model;

/**
 * Model for booking confirmation response.
 */
public class BookingResponse {
    private String bookingRef;
    private String status;
    private boolean emailSent;
    private String error;

    // Getters and Setters
    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public boolean isEmailSent() { return emailSent; }
    public void setEmailSent(boolean emailSent) { this.emailSent = emailSent; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
