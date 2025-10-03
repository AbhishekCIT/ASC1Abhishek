package com.example.airbooking.model;

import lombok.*;
import java.math.BigDecimal;

/**
 * Response model for flight search results.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchResponse {
    private Integer flightId;
    private String airline;
    private BigDecimal price;
    private Integer seatsAvailable;
    private String departure;
    private String arrival;
}
