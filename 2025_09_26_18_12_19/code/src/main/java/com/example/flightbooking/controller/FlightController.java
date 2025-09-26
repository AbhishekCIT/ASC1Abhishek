package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.*;
import com.example.flightbooking.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API controller for flight operations: search, book, and payment processing.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private UserService userService;

    /**
     * Search available flights by origin, destination, and date.
     * @param origin IATA airport code
     * @param destination IATA airport code
     * @param date Travel date (yyyy-MM-dd)
     * @return List of available flights
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FlightSearchResponse> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date) {
        return ResponseEntity.ok(flightService.searchFlights(origin, destination, date));
    }

    /**
     * Book a flight ticket.
     * @param request Booking request payload
     * @return Booking confirmation
     */
    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookingResponse> bookFlight(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(flightService.bookFlight(request));
    }
}
