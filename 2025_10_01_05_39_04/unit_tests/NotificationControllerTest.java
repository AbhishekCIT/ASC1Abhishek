package com.airline.booking.controller;

import com.airline.booking.service.NotificationService;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for NotificationController.
 * Purpose: Test email notification endpoint for normal, edge, boundary, and error scenarios.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test sending email with valid input (normal scenario).
     */
    @Test
    void sendEmail_validRequest_returnsSentStatus() throws Exception {
        Mockito.doNothing().when(notificationService).sendBookingConfirmation(anyString(), anyString(), anyString());

        NotificationController.EmailRequest request = new NotificationController.EmailRequest();
        request.setTo("john@email.com");
        request.setSubject("Booking Confirmation");
        request.setBody("Your booking is confirmed.");

        ResultActions result = mockMvc.perform(post("/api/notifications/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.status").value("SENT"));
    }

    /**
     * Test sending email with missing fields (edge case).
     */
    @Test
    void sendEmail_missingFields_returnsBadRequest() throws Exception {
        NotificationController.EmailRequest request = new NotificationController.EmailRequest();
        // Missing 'to', 'subject', 'body'
        ResultActions result = mockMvc.perform(post("/api/notifications/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    /**
     * Test sending email with invalid email address (boundary condition).
     */
    @Test
    void sendEmail_invalidEmail_returnsError() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("Invalid email address")).when(notificationService)
                .sendBookingConfirmation(anyString(), anyString(), anyString());

        NotificationController.EmailRequest request = new NotificationController.EmailRequest();
        request.setTo("invalid-email");
        request.setSubject("Booking Confirmation");
        request.setBody("Your booking is confirmed.");

        ResultActions result = mockMvc.perform(post("/api/notifications/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isInternalServerError());
    }

    /**
     * Test sending email when service throws exception (error-handling scenario).
     */
    @Test
    void sendEmail_serviceException_returnsError() throws Exception {
        Mockito.doThrow(new RuntimeException("Failed to send email")).when(notificationService)
                .sendBookingConfirmation(anyString(), anyString(), anyString());

        NotificationController.EmailRequest request = new NotificationController.EmailRequest();
        request.setTo("john@email.com");
        request.setSubject("Booking Confirmation");
        request.setBody("Your booking is confirmed.");

        ResultActions result = mockMvc.perform(post("/api/notifications/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isInternalServerError());
    }

    /**
     * Clean up mocks after each test.
     */
    @BeforeEach
    void tearDown() {
        reset(notificationService);
    }
}
