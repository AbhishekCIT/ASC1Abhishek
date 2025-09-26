package com.example.flightbooking.service;

import com.example.flightbooking.model.FlightStatus;
import org.springframework.stereotype.Component;

/**
 * Client for integrating with airline APIs to fetch real-time flight status.
 */
@Component
public class AirlineApiClient {
    public FlightStatus fetchStatus(String flightNumber) {
        // Integrate with airline API (stub implementation)
        // For demonstration, return dummy data
        FlightStatus status = new FlightStatus();
        status.setFlightNumber(flightNumber);
        status.setStatus("On Time");
        status.setDepartureTime("2025-10-01T09:00:00");
        status.setArrivalTime("2025-10-01T12:00:00");
        status.setGate("A12");
        return status;
    }
}
