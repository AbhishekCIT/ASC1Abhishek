package com.example.airbooking.model;

/**
 * Model for booking confirmation response.
 */
public class BookingConfirmation {
    private Long bookingId;
    private String status;
    private String ticket;

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTicket() { return ticket; }
    public void setTicket(String ticket) { this.ticket = ticket; }
}
