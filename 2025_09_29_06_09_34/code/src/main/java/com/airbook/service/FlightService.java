package com.airbook.service;

import com.airbook.model.Flight;
import com.airbook.repository.FlightRepository;
import com.airbook.util.AirportCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Service for flight search logic and validations
 */
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportCodeUtil airportCodeUtil;

    /**
     * Search flights based on origin, destination, date, and class
     * @param origin Airport code
     * @param destination Airport code
     * @param date Travel date (yyyy-MM-dd)
     * @param seatClass Flight class
     * @return List of matching flights
     */
    public List<Flight> searchFlights(String origin, String destination, String date, String seatClass) {
        // Validate origin and destination
        if (!airportCodeUtil.isValidAirportCode(origin)) {
            throw new IllegalArgumentException("Invalid origin airport code");
        }
        if (!airportCodeUtil.isValidAirportCode(destination)) {
            throw new IllegalArgumentException("Invalid destination airport code");
        }
        // Validate date
        LocalDate travelDate = LocalDate.parse(date);
        if (travelDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Travel date cannot be in the past");
        }
        // Search flights
        return flightRepository.findFlights(origin, destination, travelDate, seatClass);
    }
}
