package com.example.airbooking.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity representing a flight.
 */
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private double price;

    // Getters and Setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
