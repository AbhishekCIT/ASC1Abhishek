package com.airtransport.service;

import com.airtransport.model.Flight;
import com.airtransport.model.PassengerDetails;
import com.airtransport.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

/**
 * Service for integrating with airline APIs and flight data.
 */
@Service
public class AirlineIntegrationService {
    @Autowired
    private FlightRepository flightRepository;

    // Example list of valid airports
    private static final List<String> VALID_AIRPORTS = Arrays.asList("JFK", "LAX", "ORD", "ATL", "DFW");

    /**
     * Search flights by origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Date of travel
     * @return List of flights
     */
    public List<Flight> searchFlights(String origin, String destination, String date) {
        // In real implementation, query airline APIs or cached DB
        return flightRepository.findByOriginAndDestinationAndDate(origin, destination, date);
    }

    /**
     * Validate airport code.
     * @param airport Airport code
     * @return true if valid
     */
    public boolean isValidAirport(String airport) {
        return VALID_AIRPORTS.contains(airport);
    }

    /**
     * Get flight by flightId.
     * @param flightId Flight ID
     * @return Flight object
     */
    public Flight getFlightById(String flightId) {
        return flightRepository.findByFlightId(flightId).orElse(null);
    }

    /**
     * Reserve seat for a passenger on a flight.
     * @param flightId Flight ID
     * @param passengerDetails Passenger details
     */
    public void reserveSeat(String flightId, PassengerDetails passengerDetails) {
        // In real implementation, call airline API to reserve seat
        // Here, just update seat availability in DB
        flightRepository.decrementSeatsAvailable(flightId);
    }
}
