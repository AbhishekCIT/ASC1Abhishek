package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for flight inventory and airline API integration.
 */
@Service
public class FlightInventoryService {
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Get available flights based on search criteria.
     */
    public List<Flight> getAvailableFlights(String origin, String destination, String date, int passengers) {
        // TODO: Integrate with airline APIs for real-time data
        // For now, query local DB
        return flightRepository.findAvailableFlights(origin, destination, date, passengers);
    }

    /**
     * Get flight by ID.
     */
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    /**
     * Reserve seats for a booking.
     */
    public boolean reserveSeats(Long flightId, int numSeats) {
        Flight flight = getFlightById(flightId);
        if (flight == null || flight.getSeatsAvailable() < numSeats) {
            return false;
        }
        flight.setSeatsAvailable(flight.getSeatsAvailable() - numSeats);
        flightRepository.save(flight);
        return true;
    }
}
