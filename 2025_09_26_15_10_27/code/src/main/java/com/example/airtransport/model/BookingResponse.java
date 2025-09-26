package com.example.airtransport.model;

/**
 * Model representing a booking response payload.
 */
public class BookingResponse {
    private String bookingRef;
    private String status;
    private Booking ticket;

    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Booking getTicket() { return ticket; }
    public void setTicket(Booking ticket) { this.ticket = ticket; }
}
