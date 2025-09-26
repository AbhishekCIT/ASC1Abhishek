package com.airtransport.model;

/**
 * Model representing filters and sorting options for flight search.
 */
public class FlightSearchFilters {
    private String airline;
    private String sortBy; // e.g., "price", "duration", "airline"

    public FlightSearchFilters() {}

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
}
