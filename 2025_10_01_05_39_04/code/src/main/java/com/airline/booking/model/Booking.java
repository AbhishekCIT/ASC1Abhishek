package com.airline.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entity representing a booking made by a passenger.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private String status;
}
