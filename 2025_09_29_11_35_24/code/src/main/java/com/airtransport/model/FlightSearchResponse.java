package com.airtransport.model;

import java.util.List;

/**
 * Model for flight search response payload.
 */
public class FlightSearchResponse {
    private List<Flight> flights;
    private String error;

    public FlightSearchResponse() {}

    public FlightSearchResponse(List<Flight> flights) {
        this.flights = flights;
    }

    public FlightSearchResponse(String error) {
        this.error = error;
    }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
