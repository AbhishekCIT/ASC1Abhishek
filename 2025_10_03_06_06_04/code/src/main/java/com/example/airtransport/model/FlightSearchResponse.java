package com.example.airtransport.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Response model for a single flight search result.
 */
@Data
@Builder
public class FlightSearchResponse {
    private String flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private BigDecimal price;
    private String flightClass;
}
