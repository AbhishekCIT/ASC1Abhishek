package com.airbook.controller;

import com.airbook.model.Flight;
import com.airbook.service.FlightService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightController
 */
class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test searchFlights returns flights for valid input
     */
    @Test
    void testSearchFlights_ValidInput_ReturnsFlights() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2025-12-01";
        String seatClass = "Economy";
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightService.searchFlights(origin, destination, date, seatClass)).thenReturn(flights);

        ResponseEntity<List<Flight>> response = flightController.searchFlights(origin, destination, date, seatClass);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(flights, response.getBody());
    }

    /**
     * Test searchFlights with empty origin (edge case)
     */
    @Test
    void testSearchFlights_EmptyOrigin_ReturnsEmptyList() {
        String origin = "";
        String destination = "LAX";
        String date = "2025-12-01";
        String seatClass = "Economy";
        when(flightService.searchFlights(origin, destination, date, seatClass)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Flight>> response = flightController.searchFlights(origin, destination, date, seatClass);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test searchFlights with invalid date (boundary condition)
     */
    @Test
    void testSearchFlights_InvalidDate_ReturnsEmptyList() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2020-01-01"; // Past date
        String seatClass = "Economy";
        when(flightService.searchFlights(origin, destination, date, seatClass)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Flight>> response = flightController.searchFlights(origin, destination, date, seatClass);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test searchFlights with null seatClass (error scenario)
     */
    @Test
    void testSearchFlights_NullSeatClass_ReturnsEmptyList() {
        String origin = "JFK";
        String destination = "LAX";
        String date = "2025-12-01";
        String seatClass = null;
        when(flightService.searchFlights(origin, destination, date, seatClass)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Flight>> response = flightController.searchFlights(origin, destination, date, seatClass);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }
}
