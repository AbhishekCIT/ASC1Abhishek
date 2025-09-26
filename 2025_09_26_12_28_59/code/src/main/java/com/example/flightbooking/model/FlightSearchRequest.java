package com.example.flightbooking.model;

import java.time.LocalDate;

/**
 * Request payload for searching flights.
 */
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private LocalDate date;

    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
