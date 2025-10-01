package com.example.airlinebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Response model for booking confirmation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingConfirmationResponse {
    private String bookingId;
    private Map<String, Object> confirmation;
}
