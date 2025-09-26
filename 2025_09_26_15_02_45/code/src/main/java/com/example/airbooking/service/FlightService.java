package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.SeatRepository;
import com.example.airbooking.util.GDSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for flight search and seat availability
 */
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private GDSClient gdsClient;

    /**
     * Search flights by origin, destination, and date
     */
    public List<Flight> searchFlights(String origin, String destination, String date) {
        // Validate airport codes and date
        if (!isValidAirportCode(origin)) {
            throw new IllegalArgumentException("Invalid origin airport code");
        }
        if (!isValidAirportCode(destination)) {
            throw new IllegalArgumentException("Invalid destination airport code");
        }
        LocalDate travelDate = LocalDate.parse(date);
        if (travelDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Travel date cannot be in the past");
        }
        // Fetch flights from GDS
        List<Flight> flights = gdsClient.fetchFlights(origin, destination, travelDate);
        return flights;
    }

    /**
     * Get seat availability for a flight
     */
    public List<Seat> getSeats(Long flightId) {
        // Fetch seats from GDS or local DB
        return gdsClient.fetchSeats(flightId);
    }

    private boolean isValidAirportCode(String code) {
        // Example validation: must be 3 uppercase letters
        return code != null && code.matches("[A-Z]{3}");
    }
}
