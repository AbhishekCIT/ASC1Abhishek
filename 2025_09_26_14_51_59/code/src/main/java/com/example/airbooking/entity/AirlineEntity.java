package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * JPA Entity for Airline.
 */
@Entity
@Table(name = "airline")
public class AirlineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;
    private String name;
    private String code;

    public Long getAirlineId() { return airlineId; }
    public void setAirlineId(Long airlineId) { this.airlineId = airlineId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
