package com.airline.booking.controller;

import com.airline.booking.model.Flight;
import com.airline.booking.model.Seat;
import com.airline.booking.service.FlightService;
import com.airline.booking.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for flight search and seat viewing APIs.
 */
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final SeatService seatService;

    /**
     * Search flights by origin, destination, date, and class.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String flightClass) {
        List<Flight> flights = flightService.searchFlights(origin, destination, date, flightClass);
        return ResponseEntity.ok(flights);
    }

    /**
     * View available seats for a flight.
     */
    @GetMapping("/{flightId}/seats")
    public ResponseEntity<List<Seat>> viewSeats(@PathVariable String flightId) {
        Flight flight = flightService.getFlightById(flightId);
        List<Seat> seats = seatService.getAvailableSeats(flight);
        return ResponseEntity.ok(seats);
    }
}
