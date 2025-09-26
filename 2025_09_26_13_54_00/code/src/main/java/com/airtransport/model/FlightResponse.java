package com.airtransport.model;

/**
 * FlightResponse represents a flight search result.
 */
public class FlightResponse {
    private Long flightId;
    private String airline;
    private Double price;
    private String departureTime;

    public FlightResponse() {}
    public FlightResponse(Long flightId, String airline, Double price, String departureTime) {
        this.flightId = flightId;
        this.airline = airline;
        this.price = price;
        this.departureTime = departureTime;
    }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
}
