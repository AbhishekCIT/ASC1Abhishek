package com.example.flightsearch.controller;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for handling flight search requests.
 * Secured with OAuth2.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights based on user criteria.
     * @param request Flight search criteria
     * @return List of matching flights or error response
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@RequestBody FlightSearchRequest request) {
        try {
            List<FlightSearchResponse> flights = flightSearchService.searchFlights(request);
            if (flights.isEmpty()) {
                return ResponseEntity.status(404).body("No flights found matching criteria.");
            }
            return ResponseEntity.ok(flights);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal server error: " + ex.getMessage());
        }
    }
}
