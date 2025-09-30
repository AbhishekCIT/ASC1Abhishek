package com.example.airlinebooking.service;

import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.util.GDSIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Service for flight search business logic.
 */
@Service
public class FlightService {
    @Autowired
    private GDSIntegrationService gdsIntegrationService;

    /**
     * Search for flights by origin, destination, and date.
     * @param origin origin airport code
     * @param destination destination airport code
     * @param date travel date (yyyy-MM-dd)
     * @return list of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, String date) {
        if (origin == null || origin.isEmpty()) {
            throw new IllegalArgumentException("Origin cannot be empty");
        }
        if (destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
        LocalDate travelDate;
        try {
            travelDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid travel date");
        }
        if (!travelDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid travel date");
        }
        // Delegate to GDS Integration Service for real-time flight data
        return gdsIntegrationService.getAvailableFlights(origin, destination, travelDate);
    }
}
