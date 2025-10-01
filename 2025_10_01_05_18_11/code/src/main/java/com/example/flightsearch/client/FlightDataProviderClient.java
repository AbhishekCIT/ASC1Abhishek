package com.example.flightsearch.client;

import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for integrating with airline APIs to fetch flight data.
 */
@Component
public class FlightDataProviderClient {

    /**
     * Fetches flights from airline APIs based on search parameters.
     * This is a stub implementation; in production, integrate with real airline APIs.
     * @param request Flight search request
     * @return List of flights
     */
    public List<Flight> fetchFlights(FlightSearchRequest request) {
        // TODO: Integrate with real airline APIs
        // For demonstration, return mock data
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("DL123", "Delta", LocalDateTime.parse(request.getDepartureDate() + "T08:00:00"), LocalDateTime.parse(request.getDepartureDate() + "T11:00:00"), 450.0, 1, 5));
        flights.add(new Flight("UA456", "United", LocalDateTime.parse(request.getDepartureDate() + "T09:00:00"), LocalDateTime.parse(request.getDepartureDate() + "T12:30:00"), 470.0, 0, 3));
        return flights;
    }
}
