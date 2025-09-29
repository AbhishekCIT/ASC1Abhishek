package com.airtransport.client;

import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Client for integrating with external flight APIs (e.g., Amadeus, Sabre).
 * In production, this would make HTTP calls to the third-party API and parse the response.
 */
@Component
public class ExternalFlightApiClient {
    /**
     * Fetches flights from external APIs based on the search request.
     * @param request Flight search request
     * @return List of flights
     */
    public List<Flight> fetchFlights(FlightSearchRequest request) {
        // TODO: Integrate with real third-party APIs (Amadeus, Sabre, etc.)
        // For demonstration, returning mock data
        List<Flight> flights = new ArrayList<>();
        if ("JFK".equalsIgnoreCase(request.getSource()) && "LAX".equalsIgnoreCase(request.getDestination())) {
            flights.add(new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450));
            flights.add(new Flight("United", "UA456", "11:00", "14:30", "3h 30m", 470));
        }
        return flights;
    }
}
