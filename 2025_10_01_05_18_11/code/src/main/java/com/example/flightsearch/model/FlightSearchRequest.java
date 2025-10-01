package com.example.flightsearch.model;

/**
 * Model for flight search request payload.
 */
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private FlightFilters filters;
    private String sortBy;
    private Long userId;

    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public FlightFilters getFilters() { return filters; }
    public void setFilters(FlightFilters filters) { this.filters = filters; }
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
