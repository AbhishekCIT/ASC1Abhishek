package com.example.airtransport.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity for payment details.
 */
@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    private String paymentId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @Column(nullable = false)
    private String status;
}
