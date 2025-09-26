package com.airtransport.controller;

import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.model.ValidationRequest;
import com.airtransport.model.ValidationResponse;
import com.airtransport.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * REST controller for handling flight search and validation requests.
 */
@RestController
@RequestMapping("/api/v1/flights")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights based on criteria.
     * @param request Flight search request payload
     * @return List of available flights or error message
     */
    @PostMapping("/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        FlightSearchResponse response = flightSearchService.searchFlights(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Validate search input fields.
     * @param request Validation request payload
     * @return Validation result
     */
    @PostMapping("/validate")
    public ResponseEntity<ValidationResponse> validateInputs(@Valid @RequestBody ValidationRequest request) {
        ValidationResponse response = flightSearchService.validateInputs(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
