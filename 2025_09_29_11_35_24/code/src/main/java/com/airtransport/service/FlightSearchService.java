package com.airtransport.service;

import com.airtransport.client.ExternalFlightApiClient;
import com.airtransport.exception.ExternalApiException;
import com.airtransport.exception.InvalidSearchCriteriaException;
import com.airtransport.exception.NoFlightsFoundException;
import com.airtransport.filter.FlightResultFilter;
import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.repository.FlightSearchLogRepository;
import com.airtransport.util.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for handling flight search business logic.
 */
@Service
public class FlightSearchService {

    @Value("${flight.max-passenger-count:9}")
    private int maxPassengerCount;

    private final ExternalFlightApiClient externalFlightApiClient;
    private final FlightResultFilter flightResultFilter;
    private final LoggingService loggingService;
    private final FlightSearchLogRepository flightSearchLogRepository;

    @Autowired
    public FlightSearchService(ExternalFlightApiClient externalFlightApiClient,
                              FlightResultFilter flightResultFilter,
                              LoggingService loggingService,
                              FlightSearchLogRepository flightSearchLogRepository) {
        this.externalFlightApiClient = externalFlightApiClient;
        this.flightResultFilter = flightResultFilter;
        this.loggingService = loggingService;
        this.flightSearchLogRepository = flightSearchLogRepository;
    }

    /**
     * Searches for flights based on the request criteria.
     * @param request Flight search request
     * @return FlightSearchResponse containing the results
     */
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        validateRequest(request);
        loggingService.logSearchQuery(request);
        List<Flight> flights;
        try {
            flights = externalFlightApiClient.fetchFlights(request);
        } catch (Exception e) {
            throw new ExternalApiException("Failed to fetch flights from external API", e);
        }
        List<Flight> filteredFlights = flightResultFilter.applyFilters(flights, request.getFilters());
        if (filteredFlights.isEmpty()) {
            throw new NoFlightsFoundException("No flights found for the given criteria.");
        }
        // Optionally log the search result
        flightSearchLogRepository.saveSearchResult(request, filteredFlights);
        return new FlightSearchResponse(filteredFlights);
    }

    /**
     * Validates the search request fields.
     * @param request Flight search request
     */
    private void validateRequest(FlightSearchRequest request) {
        if (request.getSource() == null || request.getDestination() == null ||
            request.getSource().equalsIgnoreCase(request.getDestination())) {
            throw new InvalidSearchCriteriaException("Source and destination cannot be the same.");
        }
        if (request.getDate() == null || request.getDate().isBefore(LocalDate.now())) {
            throw new InvalidSearchCriteriaException("Travel date cannot be in the past.");
        }
        if (request.getPassengerCount() < 1 || request.getPassengerCount() > maxPassengerCount) {
            throw new InvalidSearchCriteriaException("Invalid passenger count.");
        }
    }
}
