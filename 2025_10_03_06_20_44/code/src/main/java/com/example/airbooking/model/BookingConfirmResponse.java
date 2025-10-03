package com.example.airbooking.model;

import lombok.*;

/**
 * Response model for booking confirmation status.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingConfirmResponse {
    private Integer bookingId;
    private String status;
    private String confirmationNumber;
}
