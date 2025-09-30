package com.airtransport.model;

/**
 * Response model for booking a flight.
 */
public class BookFlightResponse {
    private String bookingId;
    private String status;
    private Confirmation confirmation;

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Confirmation getConfirmation() { return confirmation; }
    public void setConfirmation(Confirmation confirmation) { this.confirmation = confirmation; }

    /**
     * Nested class for confirmation details.
     */
    public static class Confirmation {
        private boolean email;
        private boolean sms;

        // Getters and Setters
        public boolean isEmail() { return email; }
        public void setEmail(boolean email) { this.email = email; }
        public boolean isSms() { return sms; }
        public void setSms(boolean sms) { this.sms = sms; }
    }
}
