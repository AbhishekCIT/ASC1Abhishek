package com.example.airtransport.model;

import lombok.Data;

/**
 * Response model for booking a flight.
 */
@Data
public class BookingResponse {
    private String bookingId;
    private String status;
    private String eTicket;
}
