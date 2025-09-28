package com.airtransport.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a Flight.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String airline;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Double price;
    private String flightClass;

    // Getters and Setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public LocalDateTime getDeparture() { return departure; }
    public void setDeparture(LocalDateTime departure) { this.departure = departure; }
    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
}
