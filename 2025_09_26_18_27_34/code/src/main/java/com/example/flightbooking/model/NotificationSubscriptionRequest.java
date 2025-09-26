package com.example.flightbooking.model;

/**
 * Model for notification subscription request.
 */
public class NotificationSubscriptionRequest {
    private Long userId;
    private Long flightId;
    private String bookingReference;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
}
