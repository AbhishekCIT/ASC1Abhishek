package com.example.airbooking.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity representing a Flight.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name = "flight_id", nullable = false, unique = true)
    private String flightId;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "seats_available", nullable = false)
    private int seatsAvailable;

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}
