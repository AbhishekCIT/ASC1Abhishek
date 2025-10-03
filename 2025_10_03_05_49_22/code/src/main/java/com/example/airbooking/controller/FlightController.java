package com.example.airbooking.controller;

import com.example.airbooking.model.Flight;
import com.example.airbooking.service.FlightService;
import com.example.airbooking.repository.SeatRepository;
import com.example.airbooking.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for flight search and retrieval APIs.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;
    private final SeatRepository seatRepository;

    @Autowired
    public FlightController(FlightService flightService, SeatRepository seatRepository) {
        this.flightService = flightService;
        this.seatRepository = seatRepository;
    }

    /**
     * Search for available flights.
     * Example: GET /api/flights/search?from=JFK&to=LAX&date=2025-11-01&passengers=2
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam int passengers) {
        List<Flight> flights = flightService.searchFlights(from, to, date);
        if (flights.isEmpty()) {
            return ResponseEntity.status(404).body("No flights found for criteria");
        }
        // Map to response format
        List<Map<String, Object>> response = flights.stream().map(flight -> {
            Map<String, Object> map = new HashMap<>();
            map.put("flightId", flight.getId());
            map.put("airline", flight.getAirline());
            map.put("price", flight.getBasePrice());
            map.put("seatsAvailable", seatRepository.findByFlightAndIsAvailable(flight, true).size());
            return map;
        }).toList();
        return ResponseEntity.ok(response);
    }

    /**
     * View flight details and seat availability.
     * Example: GET /api/flights/{flightId}
     */
    @GetMapping("/{flightId}")
    public ResponseEntity<?> getFlight(@PathVariable Long flightId) {
        Optional<Flight> flightOpt = flightService.getFlightById(flightId);
        if (flightOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Flight ID not found");
        }
        Flight flight = flightOpt.get();
        List<Seat> seats = seatRepository.findByFlightAndIsAvailable(flight, true);
        Map<String, Object> response = new HashMap<>();
        response.put("flightId", flight.getId());
        response.put("airline", flight.getAirline());
        response.put("details", flight);
        response.put("seats", seats);
        return ResponseEntity.ok(response);
    }
}
