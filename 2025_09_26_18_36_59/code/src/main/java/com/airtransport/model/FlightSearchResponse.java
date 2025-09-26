package com.airtransport.model;

import java.util.List;

/**
 * Model representing the response for flight search API.
 */
public class FlightSearchResponse {
    private List<Flight> flights;

    public FlightSearchResponse() {}

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
