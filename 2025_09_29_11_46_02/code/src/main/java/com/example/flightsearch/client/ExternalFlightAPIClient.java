package com.example.flightsearch.client;

import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for integrating with external flight APIs (Amadeus/Sabre).
 * In a real implementation, this would make HTTP calls to the provider.
 */
@Component
public class ExternalFlightAPIClient {
    private static final Logger logger = LoggerFactory.getLogger(ExternalFlightAPIClient.class);

    /**
     * Fetches flights from external API based on search criteria.
     * @param request Search criteria
     * @return List of flights
     */
    public List<Flight> fetchFlights(FlightSearchRequest request) {
        logger.debug("Fetching flights from external API for criteria: {}", request);
        try {
            // TODO: Replace with real external API call
            // Simulated response for demonstration
            List<Flight> flights = new ArrayList<>();
            if ("JFK".equals(request.getOrigin()) && "LAX".equals(request.getDestination())) {
                flights.add(new Flight("Delta", "DL123", LocalDateTime.parse("2025-10-01T09:00:00"), LocalDateTime.parse("2025-10-01T12:00:00"), 350.00));
                flights.add(new Flight("United", "UA456", LocalDateTime.parse("2025-10-01T10:00:00"), LocalDateTime.parse("2025-10-01T13:00:00"), 340.00));
            }
            // Filtering and sorting logic can be added here based on request.getSortBy() and request.getFilter()
            return flights;
        } catch (Exception e) {
            logger.error("External API call failed: {}", e.getMessage(), e);
            throw new ExternalAPIException("Failed to fetch flights from external provider");
        }
    }
}
