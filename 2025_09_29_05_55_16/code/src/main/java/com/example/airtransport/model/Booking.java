package com.example.airtransport.model;

/**
 * Model representing a booking entity.
 */
public class Booking {
    private String bookingId;
    private String userId;
    private String flightId;
    private String status;
    private double totalPrice;
    private String bookingDate;

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
}
