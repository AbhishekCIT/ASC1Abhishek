package com.airbook.controller;

import com.airbook.model.Flight;
import com.airbook.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for flight search operations
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * Search available flights by origin, destination, date, and class
     * @param origin Airport code
     * @param destination Airport code
     * @param date Travel date (yyyy-MM-dd)
     * @param seatClass Flight class (Economy, Business, etc.)
     * @return List of matching flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date,
            @RequestParam String seatClass) {
        List<Flight> flights = flightService.searchFlights(origin, destination, date, seatClass);
        return ResponseEntity.ok(flights);
    }
}
