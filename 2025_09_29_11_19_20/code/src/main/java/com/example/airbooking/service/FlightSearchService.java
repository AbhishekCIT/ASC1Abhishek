package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for searching flights and checking availability.
 */
@Service
public class FlightSearchService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Searches for available flights by origin, destination, and date.
     * @param origin airport code
     * @param destination airport code
     * @param date date of departure
     * @return list of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(origin, destination, start, end);
    }
}
