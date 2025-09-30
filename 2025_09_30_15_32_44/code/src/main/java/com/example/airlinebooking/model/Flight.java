package com.example.airlinebooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight.
 */
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private Double price;

    public Flight() {}

    public Flight(Integer id, String airline, String origin, String destination, LocalDateTime departureTime, Double price) {
        this.id = id;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
