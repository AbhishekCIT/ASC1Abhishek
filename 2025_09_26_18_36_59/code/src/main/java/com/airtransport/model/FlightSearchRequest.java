package com.airtransport.model;

/**
 * Model representing the flight search request payload.
 */
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private FlightSearchFilters filters;

    public FlightSearchRequest() {}

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public FlightSearchFilters getFilters() { return filters; }
    public void setFilters(FlightSearchFilters filters) { this.filters = filters; }
}
