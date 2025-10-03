package com.example.airbooking.model;

import java.time.LocalTime;

/**
 * Response model for available flights
 */
public class FlightResponse {
    private String flightId;
    private String airline;
    private double price;
    private LocalTime departureTime;
    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }
}
