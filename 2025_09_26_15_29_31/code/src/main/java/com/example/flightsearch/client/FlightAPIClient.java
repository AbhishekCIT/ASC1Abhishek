package com.example.flightsearch.client;

import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles integration with external flight data APIs (e.g., Amadeus, Sabre).
 */
@Component
public class FlightAPIClient {
    /**
     * Queries external flight APIs for available flights based on the search criteria.
     * @param request Flight search request
     * @return List of available flights
     * @throws ExternalAPIException if the API call fails
     */
    public List<Flight> queryFlights(FlightSearchRequest request) throws ExternalAPIException {
        // TODO: Integrate with real external APIs (Amadeus, Sabre, etc.)
        // For demonstration, return a mock list
        List<Flight> flights = new ArrayList<>();
        // Example mock flight
        if ("JFK".equalsIgnoreCase(request.getOrigin()) && "LAX".equalsIgnoreCase(request.getDestination())) {
            flights.add(new Flight("DL123", "Delta", "2024-07-01T08:00:00", "2024-07-01T11:00:00", 350, "3h", 0));
        }
        return flights;
    }
}
