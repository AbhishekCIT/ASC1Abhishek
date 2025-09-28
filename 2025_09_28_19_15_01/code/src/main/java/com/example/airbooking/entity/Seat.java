package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a seat on a flight.
 */
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    private String seatId;
    private String flightId;
    private String seatNumber;
    private boolean isAvailable;

    public String getSeatId() { return seatId; }
    public void setSeatId(String seatId) { this.seatId = seatId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
