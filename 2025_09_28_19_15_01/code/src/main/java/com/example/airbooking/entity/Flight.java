package com.example.airbooking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    private String flightId;
    private String airline;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public LocalDateTime getDeparture() { return departure; }
    public void setDeparture(LocalDateTime departure) { this.departure = departure; }
    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
