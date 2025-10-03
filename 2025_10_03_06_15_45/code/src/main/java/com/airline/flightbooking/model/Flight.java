package com.airline.flightbooking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity representing a Flight.
 */
@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    private String flightId;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String time;
}
