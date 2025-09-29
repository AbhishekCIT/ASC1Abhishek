package com.example.flightsearch.model;

import java.util.List;

/**
 * Model representing the response for a flight search.
 */
public class FlightSearchResponse {
    private List<Flight> flights;

    public FlightSearchResponse() {}
    public FlightSearchResponse(List<Flight> flights) { this.flights = flights; }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
