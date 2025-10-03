package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Response model for searching flights.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchResponse {
    private List<FlightDto> flights;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FlightDto {
        private String flightId;
        private String airline;
        private String origin;
        private String destination;
        private String departure;
        private String arrival;
        private double price;
    }
}
