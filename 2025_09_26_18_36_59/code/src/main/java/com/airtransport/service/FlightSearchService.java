package com.airtransport.service;

import com.airtransport.client.FlightAPIClient;
import com.airtransport.model.*;
import com.airtransport.repository.FlightRepository;
import com.airtransport.repository.SearchQueryRepository;
import com.airtransport.util.AirportValidationService;
import com.airtransport.util.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for flight search logic, input validation, and orchestration.
 */
@Service
public class FlightSearchService {

    @Autowired
    private FlightAPIClient flightAPIClient;

    @Autowired
    private AirportValidationService airportValidationService;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SearchQueryRepository searchQueryRepository;

    /**
     * Search for available flights based on request criteria.
     */
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        // Validate input fields
        ValidationResponse validation = validateInputs(new ValidationRequest(request.getOrigin(), request.getDestination(), request.getDepartureDate(), request.getReturnDate()));
        if (!validation.isValid()) {
            throw new IllegalArgumentException("Validation failed: " + validation.getErrors());
        }

        // Fetch flights from third-party API or local DB
        List<Flight> flights = flightAPIClient.fetchFlights(request);
        if (flights == null || flights.isEmpty()) {
            throw new NoFlightsFoundException("No flights found for given criteria");
        }

        // Log the search query for analytics
        loggingService.logSearchQuery(request);

        // Apply filters and sorting if requested
        List<Flight> filteredFlights = applyFiltersAndSorting(flights, request.getFilters());

        // Prepare response
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(filteredFlights);
        return response;
    }

    /**
     * Validate search input fields.
     */
    public ValidationResponse validateInputs(ValidationRequest request) {
        List<String> errors = new ArrayList<>();
        boolean valid = true;

        if (!airportValidationService.isValidAirportCode(request.getOrigin())) {
            errors.add("Invalid origin airport code");
            valid = false;
        }
        if (!airportValidationService.isValidAirportCode(request.getDestination())) {
            errors.add("Invalid destination airport code");
            valid = false;
        }
        try {
            LocalDate departureDate = LocalDate.parse(request.getDepartureDate());
            if (departureDate.isBefore(LocalDate.now())) {
                errors.add("Departure date cannot be in past");
                valid = false;
            }
            if (StringUtils.hasText(request.getReturnDate())) {
                LocalDate returnDate = LocalDate.parse(request.getReturnDate());
                if (returnDate.isBefore(LocalDate.now())) {
                    errors.add("Return date cannot be in past");
                    valid = false;
                }
            }
        } catch (DateTimeParseException e) {
            errors.add("Invalid date format");
            valid = false;
        }
        return new ValidationResponse(valid, errors);
    }

    /**
     * Apply filters and sorting to the list of flights.
     */
    private List<Flight> applyFiltersAndSorting(List<Flight> flights, FlightSearchFilters filters) {
        // Filtering and sorting logic (e.g., by airline, price, duration)
        // For brevity, this is a placeholder. Implement as needed.
        return flights;
    }

    // Exception for no flights found
    public static class NoFlightsFoundException extends RuntimeException {
        public NoFlightsFoundException(String msg) { super(msg); }
    }
}
