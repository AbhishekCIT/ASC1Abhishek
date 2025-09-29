package com.airtransport.controller;

import com.airtransport.model.Flight;
import com.airtransport.service.AirlineIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for searching flights.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private AirlineIntegrationService airlineIntegrationService;

    /**
     * Search flights by origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Date of travel (yyyy-MM-dd)
     * @return List of available flights
     */
    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String origin,
                                      @RequestParam String destination,
                                      @RequestParam String date) {
        // Validate input parameters
        // Delegate to service for business logic
        return airlineIntegrationService.searchFlights(origin, destination, date);
    }
}
