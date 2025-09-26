package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Response model for flight search results.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchResponse {
    private List<FlightInfo> flights;

    /**
     * Mock response for demonstration.
     */
    public static FlightSearchResponse mockResponse(FlightSearchRequest req) {
        FlightInfo info = new FlightInfo("F123", "Delta", "10:00", "13:00", 350.00);
        return new FlightSearchResponse(List.of(info));
    }
}
