package com.airline.flightbooking.dto;

import com.airline.flightbooking.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for booking confirmation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private String bookingId;
    private String status;
    private Booking details;
}
