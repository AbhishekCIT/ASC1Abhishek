package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.*;
import com.example.flightbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for flight search and booking APIs.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    /**
     * Search for available flights based on origin, destination, and date.
     * @param searchRequest the search criteria
     * @return list of available flights
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(@RequestBody FlightSearchRequest searchRequest) {
        try {
            ValidationUtil.validateSearchRequest(searchRequest);
            List<Flight> flights = flightService.searchFlights(searchRequest.getOrigin(), searchRequest.getDestination(), searchRequest.getDate());
            return ResponseEntity.ok(flights);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal server error"));
        }
    }

    /**
     * Book a flight for a user.
     * @param bookingRequest the booking request
     * @return booking confirmation or error
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            BookingConfirmation confirmation = bookingService.bookFlight(bookingRequest);
            return ResponseEntity.ok(confirmation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal server error"));
        }
    }
}
