package com.airtransport.entity;

import jakarta.persistence.*;

/**
 * Entity representing a Passenger.
 */
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name = "passenger_id", nullable = false, unique = true)
    private String passengerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    // Getters and Setters
    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
