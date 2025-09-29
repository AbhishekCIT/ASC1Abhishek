package com.airbook.repository;

import com.airbook.model.Flight;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for flight data access
 */
@Repository
public class FlightRepository {
    /**
     * Find flights matching criteria (mock implementation)
     */
    public List<Flight> findFlights(String origin, String destination, LocalDate date, String seatClass) {
        // Replace with actual DB query
        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        flight.setFlightId("F123");
        flight.setAirline("AA");
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureDate(date);
        flight.setDepartureTime(java.time.LocalTime.of(10, 0));
        flight.setArrivalTime(java.time.LocalTime.of(13, 0));
        flight.setPrice(250.00);
        flight.setSeatClass(seatClass);
        flights.add(flight);
        return flights;
    }

    /**
     * Find flight by ID (mock implementation)
     */
    public Flight findById(String flightId) {
        // Replace with actual DB query
        if ("F123".equals(flightId)) {
            Flight flight = new Flight();
            flight.setFlightId("F123");
            flight.setAirline("AA");
            flight.setOrigin("JFK");
            flight.setDestination("LAX");
            flight.setDepartureDate(LocalDate.now().plusDays(1));
            flight.setDepartureTime(java.time.LocalTime.of(10, 0));
            flight.setArrivalTime(java.time.LocalTime.of(13, 0));
            flight.setPrice(250.00);
            flight.setSeatClass("Economy");
            return flight;
        }
        return null;
    }
}
