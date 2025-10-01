package com.example.airbooking.dto;

/**
 * DTO for flight search request.
 */
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private String tripType; // one-way, round-trip, multi-city

    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public String getTripType() { return tripType; }
    public void setTripType(String tripType) { this.tripType = tripType; }
}
