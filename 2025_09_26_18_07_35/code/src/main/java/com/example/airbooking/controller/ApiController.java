package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST API Controller for flight search and booking.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ValidationUtils validationUtils;

    /**
     * POST /api/flights/search - Search for flights.
     */
    @PostMapping("/flights/search")
    public ResponseEntity<?> searchFlights(@RequestBody SearchCriteria criteria) {
        try {
            List<FlightDTO> flights = flightSearchService.searchFlights(criteria);
            Map<String, Object> response = new HashMap<>();
            response.put("flights", flights);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * GET /api/flights/{flightId} - Get flight details.
     */
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<?> getFlightDetails(@PathVariable String flightId) {
        FlightDTO flight = flightSearchService.getFlightDetails(flightId);
        if (flight == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Flight not found");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(flight);
    }

    /**
     * POST /api/bookings - Create a booking.
     */
    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            BookingResponse response = bookingService.createBooking(request);
            if ("CONFIRMED".equals(response.getStatus())) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
