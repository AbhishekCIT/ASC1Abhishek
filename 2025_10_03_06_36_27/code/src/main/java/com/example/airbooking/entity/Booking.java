package com.example.airbooking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private String status;
    private LocalDateTime createdAt;
    // Getters and setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
