package com.example.airtransport.controller;

import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    /**
     * Searches for available flights by destination, date, and class.
     * @param request Flight search criteria
     * @return List of available flights
     */
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        List<FlightSearchResponse> flights = flightService.searchFlights(request);
        return ResponseEntity.ok(flights);
    }
}
