package com.example.airtransport.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity for flight details.
 */
@Entity
@Table(name = "flights")
@Data
public class Flight {
    @Id
    private String id;
    @Column(nullable = false)
    private String airline;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private String arrival;
    @Column(nullable = false)
    private double price;
}
