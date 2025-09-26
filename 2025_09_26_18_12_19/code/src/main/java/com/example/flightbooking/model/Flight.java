package com.example.flightbooking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Entity representing a flight.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    private String flightId;
    private String origin;
    private String destination;
    private LocalDate date;
    private double price;
    private String flightClass;
    private int availability;

    public Flight() {}
    public Flight(String flightId, String origin, String destination, LocalDate date, double price, String flightClass, int availability) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.price = price;
        this.flightClass = flightClass;
        this.availability = availability;
    }

    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
    public int getAvailability() { return availability; }
    public void setAvailability(int availability) { this.availability = availability; }
}
