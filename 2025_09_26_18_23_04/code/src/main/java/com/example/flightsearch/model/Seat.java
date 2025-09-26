package com.example.flightsearch.model;

import javax.persistence.*;

/**
 * Entity representing the SEAT table.
 */
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private long seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "seat_class")
    private String seatClass;

    @Column(name = "is_available")
    private boolean isAvailable;

    // Getters and Setters
    public long getSeatId() { return seatId; }
    public void setSeatId(long seatId) { this.seatId = seatId; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public String getSeatClass() { return seatClass; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
