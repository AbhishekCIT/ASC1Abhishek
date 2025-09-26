package com.example.flightbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a booking.
 */
@Entity
public class Booking {
    @Id
    private String bookingId;
    private String userId;
    private String flightId;
    private LocalDateTime bookingTime;
    private String status;
    private String pnr;

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
}
