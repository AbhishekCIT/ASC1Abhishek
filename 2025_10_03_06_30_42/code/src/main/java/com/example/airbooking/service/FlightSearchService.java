package com.example.airbooking.service;

import com.example.airbooking.entity.Flight;
import com.example.airbooking.exception.InvalidAirportCodeException;
import com.example.airbooking.exception.PastDateException;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for searching and filtering flights.
 */
@Service
public class FlightSearchService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Searches flights by origin, destination, and date.
     * Validates input parameters.
     * @throws InvalidAirportCodeException, PastDateException
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date) {
        if (!validationUtil.isValidIataCode(origin)) {
            throw new InvalidAirportCodeException("Invalid origin airport code");
        }
        if (!validationUtil.isValidIataCode(destination)) {
            throw new InvalidAirportCodeException("Invalid destination airport code");
        }
        if (!validationUtil.isValidTravelDate(date)) {
            throw new PastDateException("Travel date cannot be in the past");
        }
        // For demo, fetch from DB; in real system, call AirlineIntegrationService
        return flightRepository.findByOriginAndDestinationAndDate(origin, destination, date);
    }

    /**
     * Checks seat availability for a flight.
     */
    public boolean checkSeatAvailability(String flightId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        return flight != null && flight.getSeatsAvailable() > 0;
    }

    /**
     * Gets flight details by ID.
     */
    public Flight getFlightDetails(String flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }
}
