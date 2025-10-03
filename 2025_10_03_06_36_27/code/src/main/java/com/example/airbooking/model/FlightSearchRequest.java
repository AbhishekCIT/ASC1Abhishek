package com.example.airbooking.model;

import java.time.LocalDate;

/**
 * Request model for flight search
 */
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private LocalDate departureDate;
    // Getters and setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
}
