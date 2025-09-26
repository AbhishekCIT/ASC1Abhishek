package com.example.airtransport.model;

/**
 * Model representing a Seat.
 */
public class Seat {
    private String seatId;
    private String seatNumber;
    private boolean isAvailable;

    public String getSeatId() { return seatId; }
    public void setSeatId(String seatId) { this.seatId = seatId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
