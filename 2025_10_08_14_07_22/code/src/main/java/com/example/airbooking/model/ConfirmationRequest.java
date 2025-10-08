package com.example.airbooking.model;

/**
 * Request model for sending booking confirmation.
 */
public class ConfirmationRequest {
    private String bookingId;
    private String userEmail;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}
