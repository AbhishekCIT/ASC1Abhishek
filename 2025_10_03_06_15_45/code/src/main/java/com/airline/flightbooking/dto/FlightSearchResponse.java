package com.airline.flightbooking.dto;

import com.airline.flightbooking.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response DTO for flight search results.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchResponse {
    private List<Flight> flights;
}
