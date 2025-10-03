package com.asc.airbooking.model;

import lombok.Data;

/**
 * Response model for booking a flight.
 */
@Data
public class BookFlightResponse {
    private String confirmationId;
    private String status;
}
