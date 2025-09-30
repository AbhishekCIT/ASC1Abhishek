package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Flight;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for user-facing flight and booking APIs.
 */
@RestController
@RequestMapping("/api/flights")
public class UserController {
    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;

    @Autowired
    public UserController(FlightSearchService flightSearchService, BookingService bookingService) {
        this.flightSearchService = flightSearchService;
        this.bookingService = bookingService;
    }

    /**
     * Search for available flights.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Flight date
     * @return List of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<Flight> flights = flightSearchService.findFlights(origin, destination, date);
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
     * Book a flight.
     * @param request Booking request map (flightId, userId, paymentInfo)
     * @return Confirmation number if successful
     */
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody Map<String, Object> request) {
        try {
            Long flightId = ((Number) request.get("flightId")).longValue();
            Long userId = ((Number) request.get("userId")).longValue();
            Map<String, Object> paymentInfo = (Map<String, Object>) request.get("paymentInfo");
            String confirmationNumber = bookingService.bookFlight(flightId, userId, paymentInfo);
            Map<String, Object> response = new HashMap<>();
            response.put("confirmationNumber", confirmationNumber);
            response.put("status", "CONFIRMED");
            return ResponseEntity.ok(response);
        } catch (RuntimeException | IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    /**
     * Get booking details by booking ID.
     * @param bookingId Booking ID
     * @return Booking details
     */
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getBooking(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingService.getBooking(bookingId);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
