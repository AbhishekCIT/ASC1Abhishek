package com.example.airline.dto;

public class FlightSearchRequest {
    private String origin;
    private String destination;
    private String date; // ISO format (e.g., 2025-10-01)

    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}