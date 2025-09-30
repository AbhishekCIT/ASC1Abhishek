package com.example.airbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Booking entity representing a flight booking in the system.
 */
@Entity
@Table(name = "booking", uniqueConstraints = {@UniqueConstraint(columnNames = "confirmation_number")})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "confirmation_number", nullable = false, unique = true)
    private String confirmationNumber;

    @Column(nullable = false)
    private String status;

    @Column(name = "booked_at", nullable = false)
    private LocalDateTime bookedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getConfirmationNumber() { return confirmationNumber; }
    public void setConfirmationNumber(String confirmationNumber) { this.confirmationNumber = confirmationNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getBookedAt() { return bookedAt; }
    public void setBookedAt(LocalDateTime bookedAt) { this.bookedAt = bookedAt; }
}
