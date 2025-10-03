package com.example.airtransport.model;

import java.util.List;

/**
 * Model for flight search response payload.
 */
public class FlightSearchResponse {
    private List<FlightResult> flights;
    private int totalResults;

    // Getters and setters
    public List<FlightResult> getFlights() { return flights; }
    public void setFlights(List<FlightResult> flights) { this.flights = flights; }
    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }
}
