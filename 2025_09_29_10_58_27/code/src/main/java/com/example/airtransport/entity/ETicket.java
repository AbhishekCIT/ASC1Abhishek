package com.example.airtransport.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity for e-ticket details.
 */
@Entity
@Table(name = "etickets")
@Data
public class ETicket {
    @Id
    private String id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @Column(nullable = false)
    private String url;
}
