package com.airtransport.booking.model;

import lombok.Data;

@Data
public class BookingConfirmationResponse {
    private String status;
    private String eTicket;
}
