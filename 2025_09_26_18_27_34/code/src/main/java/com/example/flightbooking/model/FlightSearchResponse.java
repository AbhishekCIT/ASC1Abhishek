package com.example.flightbooking.model;

import java.util.List;

/**
 * API response for flight search.
 */
public class FlightSearchResponse {
    private List<Flight> flights;

    public FlightSearchResponse(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
