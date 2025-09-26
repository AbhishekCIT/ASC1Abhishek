package com.example.airtransport.controller;

import com.example.airtransport.model.Flight;
import com.example.airtransport.model.FlightSearchRequest;
import com.example.airtransport.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling flight search related endpoints.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    /**
     * Search for available flights based on search criteria.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param departureDate Departure date (yyyy-MM-dd)
     * @param returnDate Return date (optional, yyyy-MM-dd)
     * @param passengers Number of passengers
     * @return List of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departureDate,
            @RequestParam(required = false) String returnDate,
            @RequestParam int passengers) {
        FlightSearchRequest request = new FlightSearchRequest(origin, destination, departureDate, returnDate, passengers);
        List<Flight> flights = flightService.searchFlights(request);
        return ResponseEntity.ok(flights);
    }
}
