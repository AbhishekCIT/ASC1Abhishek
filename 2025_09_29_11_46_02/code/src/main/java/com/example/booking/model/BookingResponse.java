package com.example.booking.model;

/**
 * Model representing the booking confirmation response.
 */
public class BookingResponse {
    private String bookingReference;
    private String status;
    private String eTicket;

    public BookingResponse() {}
    public BookingResponse(String bookingReference, String status, String eTicket) {
        this.bookingReference = bookingReference;
        this.status = status;
        this.eTicket = eTicket;
    }

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getETicket() { return eTicket; }
    public void setETicket(String eTicket) { this.eTicket = eTicket; }
}
