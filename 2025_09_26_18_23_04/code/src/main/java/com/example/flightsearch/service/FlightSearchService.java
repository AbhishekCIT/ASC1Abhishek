package com.example.flightsearch.service;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.util.ValidationUtil;
import com.example.flightsearch.util.LoggingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for orchestrating flight search, validation, and integration logic.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightInventoryService flightInventoryService;
    @Autowired
    private AirlineApiIntegrationService airlineApiIntegrationService;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private LoggingUtil loggingUtil;

    /**
     * Search for flights based on request criteria.
     * @param request Flight search criteria
     * @return List of matching flights
     */
    public List<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        // Validate input
        validationUtil.validateSearchParams(request);
        // Query internal DB
        List<FlightSearchResponse> dbFlights = flightInventoryService.findFlights(request);
        // Sync with airline APIs for real-time data
        List<FlightSearchResponse> realTimeFlights = airlineApiIntegrationService.syncFlights(request);
        // Merge and filter results
        List<FlightSearchResponse> merged = mergeAndFilter(dbFlights, realTimeFlights, request);
        // Log search
        loggingUtil.logSearch(request, merged);
        return merged;
    }

    /**
     * Merge DB and real-time flights, filter and sort as per request.
     * @param dbFlights Flights from DB
     * @param realTimeFlights Flights from airline APIs
     * @param request Search criteria
     * @return Filtered and sorted flights
     */
    private List<FlightSearchResponse> mergeAndFilter(List<FlightSearchResponse> dbFlights, List<FlightSearchResponse> realTimeFlights, FlightSearchRequest request) {
        // For simplicity, combine and deduplicate by flightId, then sort/filter as per request
        List<FlightSearchResponse> all = new ArrayList<>();
        all.addAll(dbFlights);
        for (FlightSearchResponse rt : realTimeFlights) {
            boolean exists = all.stream().anyMatch(f -> f.getFlightId() == rt.getFlightId());
            if (!exists) all.add(rt);
        }
        // TODO: Apply sorting/filtering as per request (price, duration, airline, departure time)
        return all;
    }
}
