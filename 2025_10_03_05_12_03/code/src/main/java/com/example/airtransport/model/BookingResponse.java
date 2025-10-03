package com.example.airtransport.model;

import java.util.List;
import java.util.Map;

/**
 * Model representing booking confirmation response.
 */
public class BookingResponse {
    private String bookingReference;
    private String status;
    private Map<String, Object> details;

    public BookingResponse() {}
    public BookingResponse(String bookingReference, String status, Map<String, Object> details) {
        this.bookingReference = bookingReference;
        this.status = status;
        this.details = details;
    }
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Map<String, Object> getDetails() { return details; }
    public void setDetails(Map<String, Object> details) { this.details = details; }
}
