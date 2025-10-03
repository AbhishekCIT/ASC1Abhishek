package com.example.airtransport.model;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Model for individual flight result in search response.
 */
public class FlightResult {
    private String flightId;
    private String airline;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;
    private double price;
    private int stops;
    private List<String> layovers;

    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public ZonedDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(ZonedDateTime departureTime) { this.departureTime = departureTime; }
    public ZonedDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ZonedDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStops() { return stops; }
    public void setStops(int stops) { this.stops = stops; }
    public List<String> getLayovers() { return layovers; }
    public void setLayovers(List<String> layovers) { this.layovers = layovers; }
}
