package com.example.airbooking.model;

/**
 * FlightResponse represents a flight search result.
 */
public class FlightResponse {
    private Long flightId;
    private String airline;
    private Double price;
    private Integer availableSeats;

    public FlightResponse() {}
    public FlightResponse(Long flightId, String airline, Double price, Integer availableSeats) {
        this.flightId = flightId;
        this.airline = airline;
        this.price = price;
        this.availableSeats = availableSeats;
    }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
}
