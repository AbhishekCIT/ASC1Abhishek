package com.example.airtransport.model;

/**
 * Model representing cancellation request for cancellation API.
 */
public class CancellationRequest {
    private String bookingReference;
    private String reason;

    // Getters and setters
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
