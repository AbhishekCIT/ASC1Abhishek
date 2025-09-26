package com.example.flightsearch.controller;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST Controller to handle flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Exposes POST API for searching flights based on user criteria.
     * @param request Flight search request payload
     * @return List of flights matching the criteria or error response
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        try {
            FlightSearchResponse response = flightSearchService.searchFlights(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Validation error
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (ExternalAPIException e) {
            // External API error
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse("Unable to retrieve flights at this time."));
        } catch (FilterMismatchException e) {
            // No flights match filters
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No flights found for the given criteria."));
        } catch (Exception e) {
            // Generic error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("An unexpected error occurred."));
        }
    }

    /**
     * Error response model for API errors.
     */
    static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
