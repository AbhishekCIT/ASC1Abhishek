package com.example.airtransport.controller;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST Controller for searching flights.
 * Exposes endpoint for searching available air transport options.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights based on user criteria.
     * @param request Flight search criteria
     * @return List of matching flights or error
     */
    @PostMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        try {
            FlightSearchResponse response = flightSearchService.searchFlights(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal server error"));
        }
    }

    // Error response model
    static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
