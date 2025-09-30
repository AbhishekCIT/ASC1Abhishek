package com.airtransport.controller;

import com.airtransport.model.FlightDTO;
import com.airtransport.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for searching flights.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    private final FlightService flightService;

    @Autowired
    public FlightSearchController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Search available flights based on origin, destination, date, and class.
     * @param origin origin airport code
     * @param destination destination airport code
     * @param date date of departure
     * @param flightClass class (Economy, Business, etc.)
     * @return list of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "class") String flightClass) {
        List<FlightDTO> flights = flightService.searchFlights(origin, destination, date, flightClass);
        return ResponseEntity.ok(flights);
    }
}
