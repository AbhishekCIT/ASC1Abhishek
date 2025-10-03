package com.airtransport.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a seat in a flight.
 */
@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @Column(name = "seat_id", nullable = false, unique = true)
    private String seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private boolean isBooked;
}
