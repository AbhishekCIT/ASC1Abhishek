package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model for booking confirmation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingConfirmationResponse {
    private String bookingId;
    private String status;
    private BookingResponse.TicketDetails ticketDetails;
}
