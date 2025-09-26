package com.example.flightsearch.model;

import java.io.Serializable;

/**
 * Model representing a flight.
 */
public class Flight implements Serializable {
    private String flightId;
    private String airline;
    private String departure; // ISO datetime string
    private String arrival;   // ISO datetime string
    private double price;
    private String duration;
    private Integer stops;

    public Flight() {}

    public Flight(String flightId, String airline, String departure, String arrival, double price, String duration, Integer stops) {
        this.flightId = flightId;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.duration = duration;
        this.stops = stops;
    }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
    public String getArrival() { return arrival; }
    public void setArrival(String arrival) { this.arrival = arrival; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public Integer getStops() { return stops; }
    public void setStops(Integer stops) { this.stops = stops; }
}
