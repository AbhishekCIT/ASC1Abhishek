package com.example.airbooking.util;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GDSClientTest {
    private final GDSClient gdsClient = new GDSClient();

    @Test
    @DisplayName("fetchFlights - normal scenario")
    void testFetchFlightsNormal() {
        List<Flight> flights = gdsClient.fetchFlights("JFK", "LAX", LocalDate.of(2025, 10, 1));
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        Flight flight = flights.get(0);
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(350.00, flight.getPrice());
    }

    @Test
    @DisplayName("fetchFlights - edge case: empty origin/destination")
    void testFetchFlightsEmptyOriginDestination() {
        List<Flight> flights = gdsClient.fetchFlights("", "", LocalDate.of(2025, 10, 1));
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        assertEquals("", flights.get(0).getOrigin());
        assertEquals("", flights.get(0).getDestination());
    }

    @Test
    @DisplayName("fetchSeats - normal scenario")
    void testFetchSeatsNormal() {
        List<Seat> seats = gdsClient.fetchSeats(123L);
        assertNotNull(seats);
        assertEquals(2, seats.size());
        assertEquals("12A", seats.get(0).getSeatNo());
        assertTrue(seats.get(0).getAvailable());
        assertEquals("12B", seats.get(1).getSeatNo());
        assertFalse(seats.get(1).getAvailable());
    }

    @Test
    @DisplayName("fetchSeats - edge case: negative flightId")
    void testFetchSeatsNegativeFlightId() {
        List<Seat> seats = gdsClient.fetchSeats(-1L);
        assertNotNull(seats);
        assertEquals(2, seats.size());
        assertEquals(-1L, seats.get(0).getFlightId());
    }

    @Test
    @DisplayName("confirmBooking - normal scenario")
    void testConfirmBookingNormal() {
        Booking booking = new Booking();
        String ref = gdsClient.confirmBooking(booking);
        assertEquals("ABC123", ref);
    }
}
