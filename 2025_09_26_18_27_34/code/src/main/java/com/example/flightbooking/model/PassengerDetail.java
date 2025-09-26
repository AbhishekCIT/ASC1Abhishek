package com.example.flightbooking.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity/model for passenger details in a booking.
 */
@Entity
public class PassengerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;
    private String passport;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }
}
