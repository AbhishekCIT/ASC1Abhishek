package com.example.airbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private Double price;

    public Flight() {}
    public Flight(Long flightId, String origin, String destination, LocalDateTime departureTime, Double price) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
    }

    // Getters and Setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
