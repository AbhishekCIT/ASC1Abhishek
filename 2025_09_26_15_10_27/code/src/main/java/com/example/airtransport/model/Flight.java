package com.example.airtransport.model;

import java.time.LocalDateTime;

/**
 * Model representing a Flight.
 */
public class Flight {
    private String flightId;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private double price;
    private int seatsAvailable;

    public Flight() {}

    public Flight(String flightId, String origin, String destination, LocalDateTime departureTime, double price, int seatsAvailable) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}
