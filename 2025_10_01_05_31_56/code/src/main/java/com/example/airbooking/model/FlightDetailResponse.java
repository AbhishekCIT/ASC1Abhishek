package com.example.airbooking.model;

/**
 * Model for flight detail response.
 */
public class FlightDetailResponse {
    private Long flightId;
    private String airline;
    private String origin;
    private String destination;
    private String flightDate;
    private String clazz;
    private double price;
    private boolean availability;

    public FlightDetailResponse() {}
    public FlightDetailResponse(Long flightId, String airline, String origin, String destination, String flightDate, String clazz, double price, boolean availability) {
        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.clazz = clazz;
        this.price = price;
        this.availability = availability;
    }

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getFlightDate() { return flightDate; }
    public void setFlightDate(String flightDate) { this.flightDate = flightDate; }
    public String getClazz() { return clazz; }
    public void setClazz(String clazz) { this.clazz = clazz; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}
