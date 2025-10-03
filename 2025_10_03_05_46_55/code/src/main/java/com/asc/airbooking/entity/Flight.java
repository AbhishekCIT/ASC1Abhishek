package com.asc.airbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity representing a Flight record.
 */
@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    private String flightId;

    private String airline;

    private String origin;

    private String destination;

    private LocalDateTime departureTime;

    private double price;
}
