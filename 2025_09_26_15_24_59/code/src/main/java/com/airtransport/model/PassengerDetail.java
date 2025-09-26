package com.airtransport.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Model for passenger details in a booking.
 */
@Data
public class PassengerDetail {
    @NotBlank(message = "Passenger name is required")
    private String name;

    @NotBlank(message = "Passport number is required")
    private String passportNo;
}
