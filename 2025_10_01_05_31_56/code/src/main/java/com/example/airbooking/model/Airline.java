package com.example.airbooking.model;

import javax.persistence.*;

/**
 * Entity for Airline.
 */
@Entity
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;
    private String name;

    // Getters and setters
    public Long getAirlineId() { return airlineId; }
    public void setAirlineId(Long airlineId) { this.airlineId = airlineId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
