package com.example.airbooking.model;

/**
 * Model for flight search response.
 */
public class FlightResponse {
    private Long flightId;
    private String airline;
    private double price;
    private boolean availability;

    public FlightResponse() {}
    public FlightResponse(Long flightId, String airline, double price, boolean availability) {
        this.flightId = flightId;
        this.airline = airline;
        this.price = price;
        this.availability = availability;
    }

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}
