package com.airbook.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Model for Flight entity
 */
public class Flight {
    private String flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double price;
    private String seatClass;

    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getSeatClass() { return seatClass; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }
}
