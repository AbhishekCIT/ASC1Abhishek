package com.airtransport.client;

import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.model.PassengerDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ExternalFlightAPIClient.
 */
class ExternalFlightAPIClientTest {
    private ExternalFlightAPIClient client;

    @BeforeEach
    void setUp() {
        client = new ExternalFlightAPIClient();
    }

    /**
     * Test fetchAvailableFlights with valid request returns non-null response.
     */
    @Test
    @DisplayName("fetchAvailableFlights returns mock response for valid request")
    void testFetchAvailableFlights_ValidRequest() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate("2099-12-31");
        request.setPassengers(1);
        FlightSearchResponse response = client.fetchAvailableFlights(request);
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getFlights(), "Flights list should not be null");
        assertFalse(response.getFlights().isEmpty(), "Flights list should not be empty");
    }

    /**
     * Test fetchAvailableFlights with null request returns non-null response (mock behavior).
     */
    @Test
    @DisplayName("fetchAvailableFlights handles null request (mock)")
    void testFetchAvailableFlights_NullRequest() {
        FlightSearchResponse response = client.fetchAvailableFlights(null);
        assertNotNull(response, "Response should not be null even for null request (mock)");
        assertNotNull(response.getFlights(), "Flights list should not be null");
        assertFalse(response.getFlights().isEmpty(), "Flights list should not be empty");
    }

    /**
     * Test reserveSeat returns true for valid input.
     */
    @Test
    @DisplayName("reserveSeat returns true for valid input")
    void testReserveSeat_ValidInput() {
        List<PassengerDetail> passengers = List.of(new PassengerDetail("John Doe", "P1234567"));
        boolean result = client.reserveSeat("F123", passengers);
        assertTrue(result, "Reservation should succeed for valid input");
    }

    /**
     * Test reserveSeat returns true for empty passenger list (mock behavior).
     */
    @Test
    @DisplayName("reserveSeat returns true for empty passenger list (mock)")
    void testReserveSeat_EmptyPassengers() {
        boolean result = client.reserveSeat("F123", Collections.emptyList());
        assertTrue(result, "Reservation should succeed for empty passenger list (mock)");
    }

    /**
     * Test reserveSeat returns true for null passenger list (mock behavior).
     */
    @Test
    @DisplayName("reserveSeat returns true for null passenger list (mock)")
    void testReserveSeat_NullPassengers() {
        boolean result = client.reserveSeat("F123", null);
        assertTrue(result, "Reservation should succeed for null passenger list (mock)");
    }
}
