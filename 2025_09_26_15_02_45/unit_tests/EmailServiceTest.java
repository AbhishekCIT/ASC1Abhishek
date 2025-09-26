package com.example.airbooking.util;

import com.example.airbooking.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {
    private EmailService emailService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("sendConfirmationEmail - normal scenario")
    void testSendConfirmationEmailNormal() {
        Booking booking = new Booking();
        booking.setEmail("test@example.com");
        booking.setBookingRef("ABC123");
        emailService.sendConfirmationEmail(booking);
        String output = outContent.toString();
        assertTrue(output.contains("Confirmation email sent to: test@example.com for bookingRef: ABC123"));
    }

    @Test
    @DisplayName("sendConfirmationEmail - null email and bookingRef")
    void testSendConfirmationEmailNullFields() {
        Booking booking = new Booking();
        booking.setEmail(null);
        booking.setBookingRef(null);
        emailService.sendConfirmationEmail(booking);
        String output = outContent.toString();
        assertTrue(output.contains("Confirmation email sent to: null for bookingRef: null"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
