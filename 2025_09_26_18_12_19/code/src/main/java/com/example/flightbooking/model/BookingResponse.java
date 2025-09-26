package com.example.flightbooking.model;

/**
 * Response model for booking confirmation.
 */
public class BookingResponse {
    private String bookingId;
    private String status;
    private String confirmationEmail;

    public BookingResponse() {}
    public BookingResponse(String bookingId, String status, String confirmationEmail) {
        this.bookingId = bookingId;
        this.status = status;
        this.confirmationEmail = confirmationEmail;
    }

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getConfirmationEmail() { return confirmationEmail; }
    public void setConfirmationEmail(String confirmationEmail) { this.confirmationEmail = confirmationEmail; }
}
