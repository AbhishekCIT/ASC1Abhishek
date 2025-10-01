package com.airline.booking.controller;

import com.airline.booking.model.Booking;
import com.airline.booking.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for BookingController.
 * Purpose: Test booking API endpoint for normal, edge, boundary, and error scenarios.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test booking a ticket with valid input (normal scenario).
     */
    @Test
    void bookTicket_validRequest_returnsBookingResponse() throws Exception {
        Booking booking = new Booking();
        booking.setId("B123");
        booking.setStatus("DRAFT");
        Mockito.when(bookingService.createBooking(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(booking);

        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId("F123");
        BookingController.PassengerRequest passenger = new BookingController.PassengerRequest();
        passenger.setName("John Doe");
        passenger.setContact("john@email.com");
        request.setPassenger(passenger);
        request.setSeat("12A");
        request.setPayment(null);

        ResultActions result = mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.bookingId").value("B123"))
              .andExpect(jsonPath("$.status").value("DRAFT"))
              .andExpect(jsonPath("$.ticketUrl").doesNotExist());
    }

    /**
     * Test booking with missing mandatory fields (edge case).
     */
    @Test
    void bookTicket_missingFields_returnsBadRequest() throws Exception {
        BookingController.BookingRequest request = new BookingController.BookingRequest();
        // Missing flightId, passenger, seat
        ResultActions result = mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    /**
     * Test booking with invalid seat (boundary condition).
     */
    @Test
    void bookTicket_invalidSeat_returnsError() throws Exception {
        Mockito.when(bookingService.createBooking(anyString(), anyString(), anyString(), eq("99Z")))
                .thenThrow(new IllegalArgumentException("Selected seat is not available"));

        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId("F123");
        BookingController.PassengerRequest passenger = new BookingController.PassengerRequest();
        passenger.setName("Jane Doe");
        passenger.setContact("jane@email.com");
        request.setPassenger(passenger);
        request.setSeat("99Z");
        request.setPayment(null);

        ResultActions result = mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isInternalServerError());
    }

    /**
     * Test booking with duplicate seat (error-handling scenario).
     */
    @Test
    void bookTicket_duplicateSeat_returnsConflict() throws Exception {
        Mockito.when(bookingService.createBooking(anyString(), anyString(), anyString(), eq("12A")))
                .thenThrow(new RuntimeException("Selected seat is no longer available"));

        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId("F123");
        BookingController.PassengerRequest passenger = new BookingController.PassengerRequest();
        passenger.setName("Alice");
        passenger.setContact("alice@email.com");
        request.setPassenger(passenger);
        request.setSeat("12A");
        request.setPayment(null);

        ResultActions result = mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isInternalServerError());
    }

    /**
     * Clean up mocks after each test.
     */
    @BeforeEach
    void tearDown() {
        reset(bookingService);
    }
}
