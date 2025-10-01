package com.example.flightsearch.model;

import java.time.LocalDateTime;

/**
 * Model representing a flight.
 */
public class Flight {
    private String flightNumber;
    private String airline;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
    private int stops;
    private int availableSeats;

    public Flight() {}

    public Flight(String flightNumber, String airline, LocalDateTime departure, LocalDateTime arrival, double price, int stops, int availableSeats) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.stops = stops;
        this.availableSeats = availableSeats;
    }

    // Getters and Setters
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public LocalDateTime getDeparture() { return departure; }
    public void setDeparture(LocalDateTime departure) { this.departure = departure; }
    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStops() { return stops; }
    public void setStops(int stops) { this.stops = stops; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public long getDurationMinutes() {
        return java.time.Duration.between(departure, arrival).toMinutes();
    }
}
