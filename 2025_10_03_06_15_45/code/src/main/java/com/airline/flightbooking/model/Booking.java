package com.airline.flightbooking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity representing a Booking.
 */
@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    private String bookingId;

    @Column(nullable = false)
    private String status; // e.g., CONFIRMED, PENDING, CANCELLED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Column(nullable = false)
    private LocalDate bookingDate;
}
