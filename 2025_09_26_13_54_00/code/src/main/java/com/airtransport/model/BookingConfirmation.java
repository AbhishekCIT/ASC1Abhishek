package com.airtransport.model;

/**
 * BookingConfirmation is returned after a successful booking.
 */
public class BookingConfirmation {
    private Long bookingId;
    private String status;
    private String ticketNumber;
    private boolean emailSent;

    public BookingConfirmation() {}
    public BookingConfirmation(Long bookingId, String status, String ticketNumber, boolean emailSent) {
        this.bookingId = bookingId;
        this.status = status;
        this.ticketNumber = ticketNumber;
        this.emailSent = emailSent;
    }
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
    public boolean isEmailSent() { return emailSent; }
    public void setEmailSent(boolean emailSent) { this.emailSent = emailSent; }
}
