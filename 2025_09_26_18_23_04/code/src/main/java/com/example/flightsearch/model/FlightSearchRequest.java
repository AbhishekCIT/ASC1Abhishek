package com.example.flightsearch.model;

import java.time.LocalDate;

/**
 * Model representing the flight search request payload.
 */
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private LocalDate date;
    private int passengers;
    private String flightClass;

    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) { this.passengers = passengers; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }

    @Override
    public String toString() {
        return "FlightSearchRequest{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", passengers=" + passengers +
                ", flightClass='" + flightClass + '\'' +
                '}';
    }
}
