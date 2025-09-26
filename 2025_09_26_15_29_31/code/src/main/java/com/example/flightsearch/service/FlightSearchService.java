package com.example.flightsearch.service;

import com.example.flightsearch.client.FlightAPIClient;
import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.exception.FilterMismatchException;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.util.FilterService;
import com.example.flightsearch.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for flight search business logic and orchestration.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightAPIClient flightAPIClient;
    @Autowired
    private FilterService filterService;
    @Autowired
    private ValidationService validationService;

    /**
     * Searches for flights based on the given criteria.
     * @param request Flight search request
     * @return Flight search response with filtered flights
     */
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        // Validate input
        validationService.validateSearchCriteria(request);
        // Query external flight API
        List<Flight> flights = flightAPIClient.queryFlights(request);
        // Apply user-selected filters
        List<Flight> filtered = filterService.applyFilters(flights, request.getFilters());
        if (filtered == null || filtered.isEmpty()) {
            throw new FilterMismatchException("No flights found for the given criteria.");
        }
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(filtered);
        return response;
    }
}
