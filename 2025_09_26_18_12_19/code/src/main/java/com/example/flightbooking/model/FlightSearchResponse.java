package com.example.flightbooking.model;

import java.util.List;

/**
 * Response model for flight search API.
 */
public class FlightSearchResponse {
    private List<Flight> flights;

    public FlightSearchResponse() {}
    public FlightSearchResponse(List<Flight> flights) {
        this.flights = flights;
    }

    // Getters and setters
    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
