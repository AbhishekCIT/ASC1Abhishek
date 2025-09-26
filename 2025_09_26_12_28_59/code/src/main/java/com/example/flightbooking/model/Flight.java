package com.example.flightbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight.
 */
@Entity
public class Flight {
    @Id
    private String flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private int seatsAvailable;
    private double price;

    public Flight() {}

    public Flight(String flightId, String airline, String origin, String destination, LocalDateTime departureTime, int seatsAvailable, double price) {
        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
    }

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
