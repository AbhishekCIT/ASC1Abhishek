package com.example.airbooking.model;

/**
 * Model for booking confirmation and itinerary
 */
public class BookingConfirmation {
    private String bookingId;
    private String status;
    private Object itinerary; // Replace with a proper Itinerary model if needed
    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Object getItinerary() { return itinerary; }
    public void setItinerary(Object itinerary) { this.itinerary = itinerary; }
}
