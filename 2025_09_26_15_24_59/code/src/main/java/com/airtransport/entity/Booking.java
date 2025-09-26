package com.airtransport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entity representing a booking.
 */
@Entity
@Data
public class Booking {
    @Id
    private String bookingRef;
    private String userId;
    private String flightId;
    private LocalDateTime bookingDate;
    private String status;
}
