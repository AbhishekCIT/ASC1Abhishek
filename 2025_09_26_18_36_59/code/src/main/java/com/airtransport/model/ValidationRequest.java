package com.airtransport.model;

/**
 * Model representing the validation request payload.
 */
public class ValidationRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;

    public ValidationRequest() {}

    public ValidationRequest(String origin, String destination, String departureDate, String returnDate) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
}
