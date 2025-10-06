package com.example.airbooking.controller;

import com.example.airbooking.model.FlightStatus;
import com.example.airbooking.service.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for flight status operations.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightStatusController {
    @Autowired
    private FlightStatusService flightStatusService;

    /**
     * Get flight status by flight number.
     * @param flightNumber Flight number
     * @return FlightStatus
     */
    @GetMapping("/status")
    public FlightStatus getFlightStatus(@RequestParam String flightNumber) {
        return flightStatusService.getFlightStatusByFlightNumber(flightNumber);
    }
}