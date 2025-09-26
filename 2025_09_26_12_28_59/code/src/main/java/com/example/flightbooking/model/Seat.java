package com.example.flightbooking.model;

import javax.persistence.*;

/**
 * Entity representing a seat in a flight.
 */
@Entity
public class Seat {
    @Id
    private String seatId;
    private String flightId;
    private String seatNumber;
    private boolean isReserved;

    // Getters and Setters
    public String getSeatId() { return seatId; }
    public void setSeatId(String seatId) { this.seatId = seatId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public boolean isReserved() { return isReserved; }
    public void setReserved(boolean reserved) { isReserved = reserved; }
}
