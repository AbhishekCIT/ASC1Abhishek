package com.airline.flightbooking.dto;

import com.airline.flightbooking.model.Passenger;
import lombok.Data;

/**
 * Request DTO for booking a ticket.
 */
@Data
public class BookingRequest {
    private String flightId;
    private Passenger passenger;
}
