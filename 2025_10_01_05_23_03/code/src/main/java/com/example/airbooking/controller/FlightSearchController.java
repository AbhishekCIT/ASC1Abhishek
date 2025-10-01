package com.example.airbooking.controller;

import com.example.airbooking.dto.FlightSearchRequest;
import com.example.airbooking.dto.FlightSearchResponse;
import com.example.airbooking.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for flight search APIs.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights.
     * @param request Flight search request
     * @return List of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(@RequestBody FlightSearchRequest request) {
        List<FlightSearchResponse> flights = flightSearchService.searchFlights(request);
        return ResponseEntity.ok(flights);
    }
}
