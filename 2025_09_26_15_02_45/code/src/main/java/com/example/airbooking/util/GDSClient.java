package com.example.airbooking.util;

import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import com.example.airbooking.model.Booking;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility for integrating with airline GDS for flight and seat data
 */
@Component
public class GDSClient {
    /**
     * Fetch flights from GDS system
     */
    public List<Flight> fetchFlights(String origin, String destination, LocalDate date) {
        // Integrate with GDS API here
        // For demo, return dummy flights
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(123L, origin, destination, date.atTime(9, 0), 350.00));
        return flights;
    }

    /**
     * Fetch seats for a flight from GDS system
     */
    public List<Seat> fetchSeats(Long flightId) {
        // Integrate with GDS API here
        // For demo, return dummy seats
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(1L, flightId, "12A", true));
        seats.add(new Seat(2L, flightId, "12B", false));
        return seats;
    }

    /**
     * Confirm booking in GDS system
     */
    public String confirmBooking(Booking booking) {
        // Integrate with GDS API here
        // For demo, return dummy booking reference
        return "ABC123";
    }
}
