package com.example.flightsearch.controller;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.service.FlightSearchService;
import com.example.flightsearch.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Exposes POST API to search for available flights.
     * @param request Flight search request payload
     * @return List of available flights or error
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        try {
            // Validate input parameters
            validationUtil.validateSearchParams(request);
            // Call service to search flights
            FlightSearchResponse response = flightSearchService.searchFlights(request);
            return ResponseEntity.ok(response);
        } catch (com.example.flightsearch.exception.InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new com.example.flightsearch.model.ErrorResponse(e.getMessage()));
        } catch (com.example.flightsearch.exception.NoFlightsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new com.example.flightsearch.model.ErrorResponse(e.getMessage()));
        } catch (com.example.flightsearch.exception.ExternalAPIException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new com.example.flightsearch.model.ErrorResponse("Failed to fetch data from airline provider."));
        } catch (com.example.flightsearch.exception.RateLimitExceededException e) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new com.example.flightsearch.model.ErrorResponse("Too many requests. Please try again later."));
        }
    }
}
