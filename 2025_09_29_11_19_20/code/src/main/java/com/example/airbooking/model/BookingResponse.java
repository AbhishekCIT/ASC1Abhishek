package com.example.airbooking.model;

/**
 * Model for booking response returned to the client.
 */
public class BookingResponse {
    private Long bookingId;
    private String status;
    private boolean emailSent;
    private String error;

    public BookingResponse() {}

    public BookingResponse(Long bookingId, String status, boolean emailSent, String error) {
        this.bookingId = bookingId;
        this.status = status;
        this.emailSent = emailSent;
        this.error = error;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public boolean isEmailSent() { return emailSent; }
    public void setEmailSent(boolean emailSent) { this.emailSent = emailSent; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
