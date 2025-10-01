package com.airtransport.booking.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookingConfirmationRequest {
    @NotBlank(message = "Booking reference is required")
    private String bookingRef;
}
