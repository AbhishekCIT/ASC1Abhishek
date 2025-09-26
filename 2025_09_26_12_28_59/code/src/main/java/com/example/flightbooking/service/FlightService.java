package com.example.flightbooking.service;

import com.example.flightbooking.model.Flight;
import com.example.flightbooking.repository.FlightRepository;
import com.example.flightbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Service for business logic related to searching flights.
 */
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private GDSClient gdsClient;

    /**
     * Searches for available flights based on origin, destination, and date.
     * @param origin the flight origin
     * @param destination the flight destination
     * @param date the date of travel
     * @return list of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date) {
        ValidationUtil.validateSearchParams(origin, destination, date);
        // Integrate with GDS for real-time data
        List<Flight> flights = gdsClient.getAvailableFlights(origin, destination, date);
        // Filter out flights with no available seats
        flights.removeIf(flight -> flight.getSeatsAvailable() <= 0);
        return flights;
    }
}
