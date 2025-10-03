package com.airline.flightbooking.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a Payment.
 */
@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    private String paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String status; // e.g., SUCCESS, FAILED
}
