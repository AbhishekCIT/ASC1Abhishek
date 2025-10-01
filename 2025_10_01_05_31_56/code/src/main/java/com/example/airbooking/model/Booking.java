package com.example.airbooking.model;

import javax.persistence.*;
import java.time.Instant;

/**
 * Entity for Booking.
 */
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;
    private Long flightId;
    private String status;
    private Instant bookingTime;

    // Getters and setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getBookingTime() { return bookingTime; }
    public void setBookingTime(Instant bookingTime) { this.bookingTime = bookingTime; }
}
