package com.airtransport.controller;

import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST Controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/v1/flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /**
     * Search for available flights based on user criteria.
     * @param request Flight search request payload
     * @return List of matching flights or error message
     */
    @PostMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        try {
            FlightSearchResponse response = flightSearchService.searchFlights(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // Error handling for known exceptions
            return ResponseEntity.badRequest().body(new FlightSearchResponse(ex.getMessage()));
        }
    }
}
