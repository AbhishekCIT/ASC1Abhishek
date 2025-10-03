package com.example.airbooking.controller;

import com.example.airbooking.model.Flight;
import com.example.airbooking.service.FlightInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling flight search and view operations.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightInventoryService flightInventoryService;

    /**
     * Search for available flights based on origin, destination, date, and passengers.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Date of travel (yyyy-MM-dd)
     * @param passengers Number of passengers
     * @return List of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date,
            @RequestParam int passengers) {
        // Validate input
        if (origin == null || origin.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (destination == null || destination.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (date == null || date.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Flight> flights = flightInventoryService.getAvailableFlights(origin, destination, date, passengers);
        return ResponseEntity.ok(flights);
    }

    /**
     * View details of a specific flight by ID.
     * @param id Flight ID
     * @return Flight details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightInventoryService.getFlightById(id);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flight);
    }
}
