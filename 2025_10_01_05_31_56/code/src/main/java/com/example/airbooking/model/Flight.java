package com.example.airbooking.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity for Flight.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private Long airlineId;
    private String origin;
    private String destination;
    private LocalDate flightDate;
    private String clazz;
    private double price;

    // Getters and setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Long getAirlineId() { return airlineId; }
    public void setAirlineId(Long airlineId) { this.airlineId = airlineId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getFlightDate() { return flightDate; }
    public void setFlightDate(LocalDate flightDate) { this.flightDate = flightDate; }
    public String getClazz() { return clazz; }
    public void setClazz(String clazz) { this.clazz = clazz; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
