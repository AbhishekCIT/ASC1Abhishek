package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.PaymentCallbackRequest;
import com.example.airbooking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest({BookingController.class, PaymentController.class})
class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Book flight - success scenario")
    void testBookFlightSuccess() throws Exception {
        // Arrange
        Booking bookingRequest = new Booking();
        bookingRequest.setFlightId(123L);
        bookingRequest.setUserId(456L);
        bookingRequest.setEmail("test@example.com");
        Booking bookingResponse = new Booking();
        bookingResponse.setBookingRef("ABC123");
        bookingResponse.setStatus("CONFIRMED");
        when(bookingService.bookFlight(any(Booking.class))).thenReturn(bookingResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingRef").value("ABC123"))
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }

    @Test
    @DisplayName("Book flight - invalid email error")
    void testBookFlightInvalidEmail() throws Exception {
        // Arrange
        Booking bookingRequest = new Booking();
        bookingRequest.setFlightId(123L);
        bookingRequest.setUserId(456L);
        bookingRequest.setEmail("invalid-email");
        when(bookingService.bookFlight(any(Booking.class))).thenThrow(new IllegalArgumentException("Invalid email address"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Book flight - seat already booked error")
    void testBookFlightSeatAlreadyBooked() throws Exception {
        // Arrange
        Booking bookingRequest = new Booking();
        bookingRequest.setFlightId(123L);
        bookingRequest.setUserId(456L);
        bookingRequest.setEmail("test@example.com");
        when(bookingService.bookFlight(any(Booking.class))).thenThrow(new IllegalStateException("Seat already booked"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Book flight - payment failure error")
    void testBookFlightPaymentFailure() throws Exception {
        // Arrange
        Booking bookingRequest = new Booking();
        bookingRequest.setFlightId(123L);
        bookingRequest.setUserId(456L);
        bookingRequest.setEmail("test@example.com");
        when(bookingService.bookFlight(any(Booking.class))).thenThrow(new IllegalStateException("Payment could not be processed"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Payment callback - success scenario")
    void testPaymentCallbackSuccess() throws Exception {
        // Arrange
        PaymentCallbackRequest request = new PaymentCallbackRequest();
        request.setBookingRef("ABC123");
        request.setStatus("SUCCESS");
        request.setTransactionId("TXN789");
        when(bookingService.handlePaymentCallback(any(PaymentCallbackRequest.class))).thenReturn("CONFIRMED");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments/callback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("CONFIRMED"));
    }

    @Test
    @DisplayName("Payment callback - booking not found")
    void testPaymentCallbackBookingNotFound() throws Exception {
        // Arrange
        PaymentCallbackRequest request = new PaymentCallbackRequest();
        request.setBookingRef("NOTFOUND");
        request.setStatus("SUCCESS");
        request.setTransactionId("TXN000");
        when(bookingService.handlePaymentCallback(any(PaymentCallbackRequest.class))).thenReturn("BOOKING_NOT_FOUND");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments/callback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("BOOKING_NOT_FOUND"));
    }

    @Test
    @DisplayName("Payment callback - payment failed")
    void testPaymentCallbackFailed() throws Exception {
        // Arrange
        PaymentCallbackRequest request = new PaymentCallbackRequest();
        request.setBookingRef("ABC123");
        request.setStatus("FAILED");
        request.setTransactionId("TXN789");
        when(bookingService.handlePaymentCallback(any(PaymentCallbackRequest.class))).thenReturn("FAILED");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments/callback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("FAILED"));
    }
}
