package com.example.airlinebooking.service;

import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.repository.FlightRepository;
import com.example.airlinebooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for searching flights based on date, destination, and class.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Searches for flights with the given parameters.
     * @param date travel date (yyyy-MM-dd)
     * @param destination airport code
     * @param flightClass class of travel
     * @return list of available flights
     * @throws IllegalArgumentException if validation fails
     */
    @Cacheable("flights")
    public List<Flight> searchFlights(String date, String destination, String flightClass) {
        if (!validationUtil.isValidFutureDate(date)) {
            throw new IllegalArgumentException("Invalid travel date");
        }
        if (!validationUtil.isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination");
        }
        // For demo purposes, we ignore date filtering in repository (add as needed)
        return flightRepository.findByDestinationAndFlightClass(destination, flightClass);
    }
}
