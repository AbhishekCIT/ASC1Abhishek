package com.example.flightsearch.model;

import java.io.Serializable;
import java.util.List;

/**
 * Model for flight search response payload.
 */
public class FlightSearchResponse implements Serializable {
    private List<Flight> flights;

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
