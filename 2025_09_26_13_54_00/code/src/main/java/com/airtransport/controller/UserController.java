package com.airtransport.controller;

import com.airtransport.model.*;
import com.airtransport.service.*;
import com.airtransport.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

/**
 * UserController exposes REST APIs for searching flights, booking, and confirmation.
 */
@RestController
@RequestMapping("/api/flights")
public class UserController {
    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;

    /**
     * Search available flights by origin, destination, date, and airline.
     */
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FlightResponse>> searchFlights(@RequestParam String origin,
                                                             @RequestParam String destination,
                                                             @RequestParam String date,
                                                             @RequestParam(required = false) String airline) {
        List<FlightResponse> flights = flightSearchService.searchFlights(origin, destination, date, airline);
        return ResponseEntity.ok(flights);
    }

    /**
     * Book a flight for a user.
     */
    @PostMapping("/book")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            BookingConfirmation confirmation = bookingService.bookFlight(bookingRequest);
            return ResponseEntity.ok(confirmation);
        } catch (PaymentFailedException | SeatUnavailableException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getClass().getSimpleName(), e.getMessage()));
        }
    }

    /**
     * Get booking confirmation by bookingId.
     */
    @GetMapping("/confirmation/{bookingId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getConfirmation(@PathVariable Long bookingId) {
        try {
            BookingConfirmation confirmation = bookingService.getConfirmation(bookingId);
            return ResponseEntity.ok(confirmation);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("NotFoundException", e.getMessage()));
        }
    }
}
