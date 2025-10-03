package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model for booking a flight.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private String bookingId;
    private String status;
    private TicketDetails ticketDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TicketDetails {
        private String flightId;
        private String passengerName;
        private String seatNumber;
        private String departure;
        private String arrival;
    }
}
