package com.example.airlinebooking.util;

import com.example.airlinebooking.model.Flight;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility for integrating with airline GDS APIs for real-time flight data.
 */
@Component
public class GDSIntegrationService {
    /**
     * Simulates fetching available flights from a GDS API.
     * @param origin origin airport code
     * @param destination destination airport code
     * @param date travel date
     * @return list of available flights
     */
    public List<Flight> getAvailableFlights(String origin, String destination, LocalDate date) {
        // In real implementation, integrate with GDS API.
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(1, "Delta", origin, destination, date.atTime(10, 0), 350.00));
        flights.add(new Flight(2, "United", origin, destination, date.atTime(14, 30), 370.00));
        return flights;
    }
}
