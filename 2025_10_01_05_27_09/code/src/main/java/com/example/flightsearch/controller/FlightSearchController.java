package com.example.flightsearch.controller;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights based on search parameters.
     * @param request Flight search request parameters
     * @return Paginated list of available flights or error message
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid FlightSearchRequest request) {
        try {
            FlightSearchResponse response = flightSearchService.findFlights(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // Return error response with appropriate status
            return ResponseEntity.badRequest().body(new FlightSearchResponse(ex.getMessage()));
        }
    }
}
