package com.example.airtransport.model;

import lombok.Builder;
import lombok.Data;

/**
 * Response model for booking confirmation.
 */
@Data
@Builder
public class BookingResponse {
    private String bookingId;
    private String status;
    private boolean emailSent;
}
