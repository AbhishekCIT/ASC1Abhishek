package com.airtransport.booking.controller;

import com.airtransport.booking.model.FlightSearchRequest;
import com.airtransport.booking.model.FlightSearchResponse;
import com.airtransport.booking.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for flight search APIs.
 */
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightSearchController {
    private final FlightService flightService;

    /**
     * Search for available flights.
     */
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        List<FlightSearchResponse> flights = flightService.searchFlights(request);
        return ResponseEntity.ok(flights);
    }
}
