package com.example.airbooking.model;

/**
 * Response model for booking creation and confirmation.
 */
public class BookingResponse {
    private String bookingId;
    private String status;
    private String eTicket;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String geteTicket() { return eTicket; }
    public void seteTicket(String eTicket) { this.eTicket = eTicket; }
}
