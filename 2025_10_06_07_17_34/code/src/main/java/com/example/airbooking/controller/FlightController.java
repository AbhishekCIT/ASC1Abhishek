package com.example.airbooking.controller;

import com.example.airbooking.model.Flight;
import com.example.airbooking.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for flight search operations.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Search available flights by origin, destination, date, and passengers.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Travel date
     * @param passengers Number of passengers
     * @return List of available flights
     */
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam int passengers) {
        return flightSearchService.searchFlights(origin, destination, date, passengers);
    }
}