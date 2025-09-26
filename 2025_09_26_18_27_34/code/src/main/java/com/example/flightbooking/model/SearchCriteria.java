package com.example.flightbooking.model;

import java.time.LocalDate;

/**
 * Model for flight search criteria.
 */
public class SearchCriteria {
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String flightClass;
    private int passengers;

    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) { this.passengers = passengers; }
}
