package com.example.airbooking.controller;

import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import com.example.airbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for flight search and seat availability
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * Search for available flights based on origin, destination, and date
     * @param origin airport code
     * @param destination airport code
     * @param date travel date (yyyy-MM-dd)
     * @return list of flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date) {
        List<Flight> flights = flightService.searchFlights(origin, destination, date);
        return ResponseEntity.ok(flights);
    }

    /**
     * Get seat availability for a flight
     * @param flightId flight identifier
     * @return list of seats
     */
    @GetMapping("/{flightId}/seats")
    public ResponseEntity<List<Seat>> getSeats(@PathVariable Long flightId) {
        List<Seat> seats = flightService.getSeats(flightId);
        return ResponseEntity.ok(seats);
    }
}
