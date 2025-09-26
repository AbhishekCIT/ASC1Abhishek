package com.example.airbooking.model;

import javax.persistence.*;

/**
 * Entity representing a seat
 */
@Entity
@Table(name = "seat", uniqueConstraints = {@UniqueConstraint(columnNames = {"flightId", "seatNo"})})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private Long flightId;
    private String seatNo;
    private Boolean available;

    public Seat() {}
    public Seat(Long seatId, Long flightId, String seatNo, Boolean available) {
        this.seatId = seatId;
        this.flightId = flightId;
        this.seatNo = seatNo;
        this.available = available;
    }

    // Getters and Setters
    public Long getSeatId() { return seatId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getSeatNo() { return seatNo; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
