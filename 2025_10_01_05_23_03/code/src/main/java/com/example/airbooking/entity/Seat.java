package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a seat in a flight.
 */
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "class", nullable = false)
    private String seatClass;

    @Column(nullable = false)
    private boolean available;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public String getSeatClass() { return seatClass; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
