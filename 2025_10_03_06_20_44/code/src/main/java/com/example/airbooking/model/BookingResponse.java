package com.example.airbooking.model;

import lombok.*;
import java.math.BigDecimal;

/**
 * Response model for booking confirmation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Integer bookingId;
    private String status;
    private BigDecimal amount;
    private String confirmationNumber;
}
