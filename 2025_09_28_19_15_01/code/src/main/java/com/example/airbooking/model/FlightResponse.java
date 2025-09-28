package com.example.airbooking.model;

/**
 * Response model for available flights.
 */
public class FlightResponse {
    private String flightId;
    private String airline;
    private double price;
    private String departure;
    private String arrival;

    public FlightResponse() {}

    public FlightResponse(String flightId, String airline, double price, String departure, String arrival) {
        this.flightId = flightId;
        this.airline = airline;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
    public String getArrival() { return arrival; }
    public void setArrival(String arrival) { this.arrival = arrival; }
}
