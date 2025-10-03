package com.asc.airbooking.model;

import lombok.Data;
import java.util.List;

/**
 * Response model for flight search results.
 */
@Data
public class FlightSearchResponse {
    private List<FlightInfo> flights;

    @Data
    public static class FlightInfo {
        private String flightId;
        private String airline;
        private String time;
        private double price;
    }
}
