package com.airtransport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entity representing a flight.
 */
@Entity
@Data
public class Flight {
    @Id
    private String flightId;
    private String airlineId;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
}
