package com.example.airlinebooking.model;

import javax.persistence.*;

/**
 * Entity representing a flight in the airline booking system.
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    private String flightId;
    private String departure;
    private String destination;
    private String flightClass;
    private int seatsAvailable;

    // Getters and Setters
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
}
