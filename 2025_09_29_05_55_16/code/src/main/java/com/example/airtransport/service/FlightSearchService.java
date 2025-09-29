package com.example.airtransport.service;

import com.example.airtransport.model.Flight;
import com.example.airtransport.util.ExternalFlightAPIClient;
import com.example.airtransport.exception.ExternalAPIException;
import com.example.airtransport.exception.SeatUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for searching flights and checking seat availability.
 */
@Service
public class FlightSearchService {

    @Autowired
    private ExternalFlightAPIClient externalFlightAPIClient;

    /**
     * Search for available flights using external APIs.
     * @param destination Destination airport code.
     * @param date Date of travel.
     * @param passengers Number of passengers.
     * @return List of available flights.
     * @throws ExternalAPIException if the external API fails.
     */
    @Cacheable("flightSearch")
    public List<Flight> searchFlights(String destination, String date, int passengers) throws ExternalAPIException {
        return externalFlightAPIClient.getFlights(destination, date, passengers);
    }

    /**
     * Check if seats are available for a given flight.
     * @param flightId Flight ID.
     * @return true if seats are available, false otherwise.
     * @throws SeatUnavailableException if no seats are available.
     */
    public boolean isSeatAvailable(String flightId) throws SeatUnavailableException {
        Flight flight = externalFlightAPIClient.getFlightById(flightId);
        if (flight == null || flight.getSeatsAvailable() <= 0) {
            throw new SeatUnavailableException("Seat not available");
        }
        return true;
    }
}
