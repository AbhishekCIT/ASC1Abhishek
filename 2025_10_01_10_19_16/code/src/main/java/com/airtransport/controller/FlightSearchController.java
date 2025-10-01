package com.airtransport.controller;

import com.airtransport.model.Flight;
import com.airtransport.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * Search for available flights based on origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Travel date (yyyy-MM-dd)
     * @return List of available flights
     */
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> searchFlights(@RequestParam String origin,
                                           @RequestParam String destination,
                                           @RequestParam String date) {
        try {
            List<Flight> flights = flightService.searchFlights(origin, destination, date);
            return ResponseEntity.ok().body(new FlightSearchResponse(flights));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }

    // Response wrapper for flights
    public static class FlightSearchResponse {
        private List<Flight> flights;
        public FlightSearchResponse(List<Flight> flights) { this.flights = flights; }
        public List<Flight> getFlights() { return flights; }
        public void setFlights(List<Flight> flights) { this.flights = flights; }
    }
    // Error response wrapper
    public static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
