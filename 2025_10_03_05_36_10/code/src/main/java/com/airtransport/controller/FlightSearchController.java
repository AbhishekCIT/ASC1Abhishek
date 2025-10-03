package com.airtransport.controller;

import com.airtransport.model.Flight;
import com.airtransport.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    @Autowired
    private FlightService flightService;

    /**
     * Search flights API
     * @param date Departure date
     * @param destination Destination airport code
     * @param passengers Number of passengers (not used for filtering in this example)
     * @return List of flights
     */
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("destination") String destination,
            @RequestParam(value = "passengers", required = false) Integer passengers
    ) {
        return flightService.searchFlights(destination, date);
    }
}
