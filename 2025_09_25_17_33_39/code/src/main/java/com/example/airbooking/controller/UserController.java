package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import com.example.airbooking.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.List;

/**
 * UserController exposes REST endpoints for searching flights and booking tickets.
 */
@RestController
@RequestMapping("/api/flights")
public class UserController {
    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;

    /**
     * Search for available flights based on criteria.
     * @param from source airport code
     * @param to destination airport code
     * @param date date of travel
     * @param passengers number of passengers
     * @return list of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String date,
            @RequestParam int passengers) {
        List<FlightResponse> flights = flightSearchService.searchFlights(from, to, date, passengers);
        return ResponseEntity.ok(flights);
    }

    /**
     * Book a flight for a user.
     * @param request booking request payload
     * @param authentication OAuth2 authentication token
     * @return booking confirmation or error
     */
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest request, JwtAuthenticationToken authentication) {
        try {
            BookingConfirmation confirmation = bookingService.bookFlight(request, authentication);
            return ResponseEntity.ok(confirmation);
        } catch (NoSeatsAvailableException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("NO_SEATS", e.getMessage()));
        } catch (PaymentFailedException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PAYMENT_FAILED", e.getMessage()));
        } catch (AirlineApiException e) {
            return ResponseEntity.status(502).body(new ErrorResponse("AIRLINE_API_ERROR", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorResponse("INTERNAL_ERROR", "Unexpected error occurred."));
        }
    }
}
