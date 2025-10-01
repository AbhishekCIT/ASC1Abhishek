package com.example.airlinebooking.model;

import javax.persistence.*;

/**
 * Entity representing a passenger in the airline booking system.
 */
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    private String passengerId;
    private String name;
    private String email;
    private String phone;

    // Getters and Setters
    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
