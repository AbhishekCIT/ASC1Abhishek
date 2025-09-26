package com.example.flightbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity/model for notification subscription.
 */
@Entity
public class NotificationSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Flight flight;
    private LocalDateTime subscribedAt;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public LocalDateTime getSubscribedAt() { return subscribedAt; }
    public void setSubscribedAt(LocalDateTime subscribedAt) { this.subscribedAt = subscribedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
