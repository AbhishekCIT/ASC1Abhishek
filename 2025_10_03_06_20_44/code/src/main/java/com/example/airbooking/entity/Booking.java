package com.example.airbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity representing a booking.
 */
@Entity
@Table(name = "bookings", uniqueConstraints = {@UniqueConstraint(columnNames = {"confirmationNumber"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, unique = true)
    private String confirmationNumber;

    @Column(nullable = false)
    private LocalDateTime bookedAt;
}
