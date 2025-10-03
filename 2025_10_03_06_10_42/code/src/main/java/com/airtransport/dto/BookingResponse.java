package com.airtransport.dto;

import java.util.Map;

/**
 * DTO for booking response payload.
 */
public class BookingResponse {
    private String bookingReference;
    private String status;
    private Map<String, Object> receipt;
    private Map<String, Object> itinerary;

    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Map<String, Object> getReceipt() { return receipt; }
    public void setReceipt(Map<String, Object> receipt) { this.receipt = receipt; }
    public Map<String, Object> getItinerary() { return itinerary; }
    public void setItinerary(Map<String, Object> itinerary) { this.itinerary = itinerary; }
}
