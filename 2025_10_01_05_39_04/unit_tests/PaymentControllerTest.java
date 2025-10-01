package com.airline.booking.controller;

import com.airline.booking.model.Booking;
import com.airline.booking.model.Payment;
import com.airline.booking.model.Ticket;
import com.airline.booking.repository.BookingRepository;
import com.airline.booking.repository.TicketRepository;
import com.airline.booking.service.BookingService;
import com.airline.booking.service.NotificationService;
import com.airline.booking.service.PaymentService;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for PaymentController.
 * Purpose: Test payment processing endpoint for normal, edge, boundary, and error scenarios.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;
    @MockBean
    private BookingRepository bookingRepository;
    @MockBean
    private BookingService bookingService;
    @MockBean
    private TicketRepository ticketRepository;
    @MockBean
    private NotificationService notificationService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test processing payment with valid input and successful payment (normal scenario).
     */
    @Test
    void processPayment_successful_returnsSuccessStatus() throws Exception {
        Payment payment = new Payment();
        payment.setId("P123");
        payment.setStatus("SUCCESS");
        Mockito.when(paymentService.processPayment(anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(payment);
        Booking booking = new Booking();
        booking.setId("B123");
        booking.setPassenger(new com.airline.booking.model.Passenger());
        booking.getPassenger().setContact("john@email.com");
        Mockito.when(bookingRepository.findById(eq("B123"))).thenReturn(Optional.of(booking));
        Mockito.doNothing().when(bookingService).confirmBooking(any(Booking.class));
        Mockito.doNothing().when(ticketRepository).save(any(Ticket.class));
        Mockito.doNothing().when(notificationService).sendBookingConfirmation(anyString(), anyString(), anyString());

        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId("B123");
        request.setAmount(BigDecimal.valueOf(350.00));
        request.setMethod("CreditCard");
        request.setDetails(null);

        ResultActions result = mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.paymentId").value("P123"))
              .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    /**
     * Test processing payment with failed payment (edge case).
     */
    @Test
    void processPayment_failed_returnsFailureStatus() throws Exception {
        Payment payment = new Payment();
        payment.setId("P124");
        payment.setStatus("FAILED");
        Mockito.when(paymentService.processPayment(anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(payment);

        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId("B124");
        request.setAmount(BigDecimal.valueOf(350.00));
        request.setMethod("CreditCard");
        request.setDetails(null);

        ResultActions result = mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.paymentId").value("P124"))
              .andExpect(jsonPath("$.status").value("FAILED"));
    }

    /**
     * Test processing payment with missing fields (boundary condition).
     */
    @Test
    void processPayment_missingFields_returnsBadRequest() throws Exception {
        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        // Missing bookingId, amount, method
        ResultActions result = mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    /**
     * Test processing payment for non-existent booking (error-handling scenario).
     */
    @Test
    void processPayment_bookingNotFound_returnsError() throws Exception {
        Payment payment = new Payment();
        payment.setId("P125");
        payment.setStatus("SUCCESS");
        Mockito.when(paymentService.processPayment(anyString(), any(BigDecimal.class), anyString()))
                .thenReturn(payment);
        Mockito.when(bookingRepository.findById(eq("B999"))).thenReturn(Optional.empty());

        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId("B999");
        request.setAmount(BigDecimal.valueOf(350.00));
        request.setMethod("CreditCard");
        request.setDetails(null);

        ResultActions result = mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isInternalServerError());
    }

    /**
     * Clean up mocks after each test.
     */
    @BeforeEach
    void tearDown() {
        reset(paymentService, bookingRepository, bookingService, ticketRepository, notificationService);
    }
}
