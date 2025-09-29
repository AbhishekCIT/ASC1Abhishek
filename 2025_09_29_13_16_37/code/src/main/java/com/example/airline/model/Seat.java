package com.example.airline.model;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    private String seatId;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    private String seatNumber;
    private boolean isAvailable;

    // Getters and Setters
    public String getSeatId() { return seatId; }
    public void setSeatId(String seatId) { this.seatId = seatId; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}