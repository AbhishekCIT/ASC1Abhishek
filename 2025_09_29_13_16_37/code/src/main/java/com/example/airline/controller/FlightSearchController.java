package com.example.airline.controller;

import com.example.airline.dto.FlightSearchRequest;
import com.example.airline.dto.FlightSearchResponse;
import com.example.airline.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search for available flights by origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Date of travel (YYYY-MM-DD)
     * @return List of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date) {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin(origin);
        request.setDestination(destination);
        request.setDate(date);
        List<FlightSearchResponse> flights = flightSearchService.searchFlights(request);
        return ResponseEntity.ok(flights);
    }
}