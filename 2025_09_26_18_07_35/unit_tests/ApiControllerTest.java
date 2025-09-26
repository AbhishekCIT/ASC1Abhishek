package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.util.ValidationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for ApiController.
 */
public class ApiControllerTest {
    private MockMvc mockMvc;

    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private ValidationUtils validationUtils;

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    /**
     * Test normal scenario for searching flights.
     */
    @Test
    @DisplayName("POST /api/flights/search - success")
    void testSearchFlightsSuccess() throws Exception {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setOrigin("JFK");
        criteria.setDestination("LAX");
        criteria.setDepartureDate(LocalDate.now().plusDays(1));
        criteria.setPassengers(1);
        List<FlightDTO> flights = Collections.singletonList(new FlightDTO());
        when(flightSearchService.searchFlights(any())).thenReturn(flights);

        String json = "{\"origin\":\"JFK\",\"destination\":\"LAX\",\"departureDate\":\"" + LocalDate.now().plusDays(1) + "\",\"passengers\":1}";

        mockMvc.perform(post("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flights", hasSize(1)));
    }

    /**
     * Test error scenario for searching flights with invalid input.
     */
    @Test
    @DisplayName("POST /api/flights/search - invalid input")
    void testSearchFlightsInvalidInput() throws Exception {
        when(flightSearchService.searchFlights(any())).thenThrow(new IllegalArgumentException("Origin and destination cannot be the same"));
        String json = "{\"origin\":\"JFK\",\"destination\":\"JFK\",\"departureDate\":\"" + LocalDate.now().plusDays(1) + "\",\"passengers\":1}";
        mockMvc.perform(post("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Origin and destination cannot be the same")));
    }

    /**
     * Test normal scenario for getting flight details.
     */
    @Test
    @DisplayName("GET /api/flights/{flightId} - success")
    void testGetFlightDetailsSuccess() throws Exception {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId("UA123");
        when(flightSearchService.getFlightDetails("UA123")).thenReturn(dto);
        mockMvc.perform(get("/api/flights/UA123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId", is("UA123")));
    }

    /**
     * Test error scenario for getting flight details when flight not found.
     */
    @Test
    @DisplayName("GET /api/flights/{flightId} - not found")
    void testGetFlightDetailsNotFound() throws Exception {
        when(flightSearchService.getFlightDetails("UA999")).thenReturn(null);
        mockMvc.perform(get("/api/flights/UA999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Flight not found")));
    }

    /**
     * Test normal scenario for creating a booking.
     */
    @Test
    @DisplayName("POST /api/bookings - success")
    void testCreateBookingSuccess() throws Exception {
        BookingResponse response = new BookingResponse();
        response.setBookingRef("BR123456");
        response.setStatus("CONFIRMED");
        response.setEmailSent(true);
        when(bookingService.createBooking(any())).thenReturn(response);
        String json = "{\"flightId\":\"UA123\",\"userId\":\"u001\",\"passengerInfo\":{},\"paymentInfo\":{\"cardNumber\":\"1234\",\"cvv\":\"123\",\"amount\":350}}";
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingRef", is("BR123456")))
                .andExpect(jsonPath("$.status", is("CONFIRMED")))
                .andExpect(jsonPath("$.emailSent", is(true)));
    }

    /**
     * Test error scenario for creating a booking with invalid input.
     */
    @Test
    @DisplayName("POST /api/bookings - invalid input")
    void testCreateBookingInvalidInput() throws Exception {
        when(bookingService.createBooking(any())).thenThrow(new IllegalArgumentException("Incomplete booking request"));
        String json = "{\"flightId\":null,\"userId\":\"u001\",\"passengerInfo\":{},\"paymentInfo\":{}}";
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Incomplete booking request")));
    }

    /**
     * Test error scenario for booking not confirmed (e.g., payment failed).
     */
    @Test
    @DisplayName("POST /api/bookings - booking not confirmed")
    void testCreateBookingNotConfirmed() throws Exception {
        BookingResponse response = new BookingResponse();
        response.setBookingRef("BR123456");
        response.setStatus("FAILED");
        response.setEmailSent(false);
        response.setError("Payment failed");
        when(bookingService.createBooking(any())).thenReturn(response);
        String json = "{\"flightId\":\"UA123\",\"userId\":\"u001\",\"passengerInfo\":{},\"paymentInfo\":{\"cardNumber\":\"1234\",\"cvv\":\"123\",\"amount\":350}}";
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAILED")))
                .andExpect(jsonPath("$.error", is("Payment failed")));
    }
}
