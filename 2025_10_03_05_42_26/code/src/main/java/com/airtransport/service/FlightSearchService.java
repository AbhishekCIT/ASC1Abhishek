package com.airtransport.service;

import com.airtransport.model.Flight;
import com.airtransport.util.AirportCodeValidatorUtil;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for searching flights.
 */
@Service
public class FlightSearchService {
    /**
     * Searches flights based on origin, destination, and date.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Date of travel (yyyy-MM-dd)
     * @return List of available flights
     */
    public List<Flight> searchFlights(String origin, String destination, String date) {
        // Validate airport codes
        if (!AirportCodeValidatorUtil.isValidAirportCode(origin)) {
            throw new RuntimeException("Invalid origin airport code");
        }
        if (!AirportCodeValidatorUtil.isValidAirportCode(destination)) {
            throw new RuntimeException("Invalid destination airport code");
        }
        // Validate date
        LocalDate travelDate = LocalDate.parse(date);
        if (travelDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Date cannot be in the past");
        }
        // TODO: Integrate with airline flight data provider
        // For demo, return dummy list
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F123", "Delta", origin, destination, date, "10:00", 200, 10));
        return flights;
    }
}
