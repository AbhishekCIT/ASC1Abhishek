package com.airtransport.controller;

import com.airtransport.dto.SearchRequest;
import com.airtransport.dto.SearchResponse;
import com.airtransport.exception.InvalidLocationException;
import com.airtransport.exception.PastDateException;
import com.airtransport.exception.ProviderAPIException;
import com.airtransport.exception.ValidationException;
import com.airtransport.service.FlightProviderService;
import com.airtransport.service.LocationService;
import com.airtransport.service.SearchFilterService;
import com.airtransport.util.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Controller to handle air transport search API requests.
 */
@RestController
@RequestMapping("/api/air")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private FlightProviderService flightProviderService;

    @Autowired
    private SearchFilterService searchFilterService;

    @Autowired
    private ErrorHandler errorHandler;

    /**
     * POST endpoint to search for available flights.
     * @param request SearchRequest JSON payload
     * @return SearchResponse or error
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody SearchRequest request) {
        logger.debug("Received search request: {}", request);
        try {
            // Validate origin and destination
            if (request.getOrigin() == null || request.getOrigin().trim().isEmpty()) {
                throw new InvalidLocationException("Origin location is required.");
            }
            if (request.getDestination() == null || request.getDestination().trim().isEmpty()) {
                throw new InvalidLocationException("Destination location is required.");
            }
            if (!locationService.validateLocation(request.getOrigin())) {
                throw new InvalidLocationException("Invalid origin location.");
            }
            if (!locationService.validateLocation(request.getDestination())) {
                throw new InvalidLocationException("Invalid destination location.");
            }
            // Validate date
            LocalDate travelDate;
            try {
                travelDate = LocalDate.parse(request.getDate());
            } catch (DateTimeParseException e) {
                throw new ValidationException("Invalid date format. Use YYYY-MM-DD.");
            }
            if (travelDate.isBefore(LocalDate.now())) {
                throw new PastDateException("Travel date cannot be in the past.");
            }
            // Query flight providers
            var results = flightProviderService.queryProviders(request);
            // Apply filters and sorting
            var filteredResults = searchFilterService.applyFilters(results, request.getPreferences());
            SearchResponse response = new SearchResponse();
            response.setResults(filteredResults);
            logger.info("Search successful. Results count: {}", filteredResults.size());
            return ResponseEntity.ok(response);
        } catch (InvalidLocationException | PastDateException | ValidationException e) {
            logger.error("Validation error: {}", e.getMessage());
            return errorHandler.handleError(e);
        } catch (ProviderAPIException e) {
            logger.error("External provider API error: {}", e.getMessage());
            return errorHandler.handleError(e);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage(), e);
            return errorHandler.handleError(new ValidationException("Internal server error."));
        }
    }
}
