package com.example.airbooking.model;

/**
 * Model for booking response payload.
 */
public class BookingResponse {
    private Long bookingId;
    private String status;
    private String confirmation;

    public BookingResponse() {}
    public BookingResponse(Long bookingId, String status, String confirmation) {
        this.bookingId = bookingId;
        this.status = status;
        this.confirmation = confirmation;
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getConfirmation() { return confirmation; }
    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }
}
