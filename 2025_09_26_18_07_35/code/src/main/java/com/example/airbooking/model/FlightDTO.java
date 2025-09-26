package com.example.airbooking.model;

/**
 * DTO for flight search results and details.
 */
public class FlightDTO {
    private String flightId;
    private double price;
    private String duration;
    private String airline;
    private Object details; // Can be refined

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public Object getDetails() { return details; }
    public void setDetails(Object details) { this.details = details; }
}
