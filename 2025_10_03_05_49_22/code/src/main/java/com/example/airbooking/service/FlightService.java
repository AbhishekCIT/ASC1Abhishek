package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Service for flight search and retrieval logic.
 */
@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Searches for flights based on criteria.
     * Caches results for 1 minute.
     */
    @Cacheable(value = "flightSearchCache", key = "#from + #to + #date", unless = "#result == null", cacheManager = "cacheManager")
    public List<Flight> searchFlights(String from, String to, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return flightRepository.findByFromAirportAndToAirportAndDepartureBetween(from, to, start, end);
    }

    /**
     * Retrieves a flight by its ID.
     */
    public Optional<Flight> getFlightById(Long flightId) {
        return flightRepository.findById(flightId);
    }
}
