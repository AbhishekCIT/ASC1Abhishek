package com.airtransport.booking.controller;

import com.airtransport.booking.entity.Flight;
import com.airtransport.booking.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for flight search APIs.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search available flights.
     * @param origin Origin airport code/name
     * @param destination Destination airport code/name
     * @param date Travel date
     * @param flightClass Class of service
     * @return List of flights
     */
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "class") String flightClass) {
        List<Flight> flights = flightSearchService.searchFlights(origin, destination, date, flightClass);
        return ResponseEntity.ok(flights);
    }
}
