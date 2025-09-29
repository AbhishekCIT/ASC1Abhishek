package com.example.flightsearch.controller;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * REST controller for searching available flights.
 * Handles POST requests to /api/flights/search
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    private static final Logger logger = LoggerFactory.getLogger(FlightSearchController.class);

    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights based on criteria.
     * @param request Flight search criteria
     * @return List of matching flights or error message
     */
    @PostMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        logger.info("User requested flight search: {}", request);
        try {
            FlightSearchResponse response = flightSearchService.findFlights(request);
            if (response.getFlights().isEmpty()) {
                logger.warn("No flights found for criteria: {}", request);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No flights found matching criteria."));
            }
            logger.info("Flight search successful for criteria: {}", request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Flight search failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Simple error response model
     */
    static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
