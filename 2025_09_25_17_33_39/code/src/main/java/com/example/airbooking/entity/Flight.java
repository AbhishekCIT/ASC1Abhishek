package com.example.airbooking.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Flight entity represents a flight operated by an airline.
 */
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airline;
    private String fromAirport;
    private String toAirport;
    private LocalDate date;
    private Integer availableSeats;
    private Double price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public String getFromAirport() { return fromAirport; }
    public void setFromAirport(String fromAirport) { this.fromAirport = fromAirport; }
    public String getToAirport() { return toAirport; }
    public void setToAirport(String toAirport) { this.toAirport = toAirport; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
