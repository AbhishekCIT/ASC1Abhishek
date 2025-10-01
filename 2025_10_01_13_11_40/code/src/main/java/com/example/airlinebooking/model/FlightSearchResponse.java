package com.example.airlinebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response model for available flights.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchResponse {
    private String flightId;
    private Double fare;
    private List<String> seats;
}
