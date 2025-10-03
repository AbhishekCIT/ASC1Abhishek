package com.example.airtransport.controller;

import com.example.airtransport.model.FlightSearchCriteria;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.service.FlightSearchService;
import com.example.airtransport.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    private static final Logger logger = LoggerFactory.getLogger(FlightSearchController.class);

    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Exposes POST API endpoint for searching flights.
     * @param criteria Search criteria
     * @return Paginated flight search results or error
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchCriteria criteria) {
        logger.info("Received flight search request: {}", criteria);
        try {
            validationUtil.validateSearchCriteria(criteria);
            FlightSearchResponse response = flightSearchService.searchFlights(criteria);
            if (response.getFlights().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flights found for the given criteria.");
            }
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid input parameters: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
