package com.example.airtransport.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

/**
 * Entity for booking details.
 */
@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    private String id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ElementCollection
    private Map<String, Object> passengerInfo;
    @Column(nullable = false)
    private String status;
}
