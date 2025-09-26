package com.example.airtransport.controller;

import com.example.airtransport.model.Flight;
import com.example.airtransport.service.FlightInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightInventoryService flightInventoryService;

    /**
     * Search for available flights based on origin, destination, and date.
     * @param origin IATA code for origin airport
     * @param destination IATA code for destination airport
     * @param date Date of travel (yyyy-MM-dd)
     * @return List of available flights
     */
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> searchFlights(@RequestParam String origin,
                                           @RequestParam String destination,
                                           @RequestParam String date) {
        // Validate input
        if (origin == null || origin.isEmpty() || origin.length() != 3) {
            return ResponseEntity.badRequest().body("Invalid origin airport code");
        }
        if (destination == null || destination.isEmpty() || destination.length() != 3) {
            return ResponseEntity.badRequest().body("Invalid destination airport code");
        }
        LocalDate travelDate;
        try {
            travelDate = LocalDate.parse(date);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use yyyy-MM-dd");
        }
        if (travelDate.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Travel date cannot be in the past");
        }
        List<Flight> flights = flightInventoryService.searchFlights(origin.toUpperCase(), destination.toUpperCase(), travelDate);
        return ResponseEntity.ok().body(flights);
    }
}
