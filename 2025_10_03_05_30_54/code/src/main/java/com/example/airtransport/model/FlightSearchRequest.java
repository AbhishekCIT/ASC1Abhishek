package com.example.airtransport.model;

import java.util.Map;

/**
 * Model for flight search request payload.
 */
public class FlightSearchRequest {
    private String userId;
    private String origin;
    private String destination;
    private String departureDate; // yyyy-MM-dd
    private String returnDate; // yyyy-MM-dd (optional)
    private Map<String, Object> filters; // airline, priceRange, stops
    private String sortBy; // price, duration, departureTime

    // Getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public Map<String, Object> getFilters() { return filters; }
    public void setFilters(Map<String, Object> filters) { this.filters = filters; }
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
}
