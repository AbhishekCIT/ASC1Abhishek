package com.example.flightbooking.model;

/**
 * Response payload for booking confirmation.
 */
public class BookingConfirmation {
    private String bookingId;
    private String status;
    private String pnr;

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
}
