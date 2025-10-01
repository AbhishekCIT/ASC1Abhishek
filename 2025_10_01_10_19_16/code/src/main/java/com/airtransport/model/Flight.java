package com.airtransport.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name = "flight_id")
    private String flightId;
    private String airline;
    private String origin;
    private String destination;
    private String time; // Departure time as string (for search API)
    private String arrivalTime; // Arrival time as string
    private double price;
    private int seatsAvailable;

    public Flight() {}
    public Flight(String flightId, String airline, String time, String arrivalTime, String origin, String destination, double price, int seatsAvailable) {
        this.flightId = flightId;
        this.airline = airline;
        this.time = time;
        this.arrivalTime = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }
    // Getters and setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}
