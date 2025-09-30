package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling flight search requests.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    @Autowired
    private FlightService flightService;

    /**
     * Search for available flights by origin, destination, and date.
     * @param origin the origin airport code
     * @param destination the destination airport code
     * @param date the travel date (yyyy-MM-dd)
     * @return list of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam String origin,
                                                     @RequestParam String destination,
                                                     @RequestParam String date) {
        List<Flight> flights = flightService.searchFlights(origin, destination, date);
        return ResponseEntity.ok(flights);
    }
}
