package com.airline.flightbooking.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a Passenger.
 */
@Entity
@Table(name = "passenger")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {
    @Id
    private String passengerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String id; // Government-issued ID or similar
}
