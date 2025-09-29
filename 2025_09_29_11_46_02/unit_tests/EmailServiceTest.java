package com.example.booking.client;

import com.example.booking.model.Booking;
import com.example.booking.model.PassengerDetails;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;

/**
 * Unit tests for EmailService.
 */
class EmailServiceTest {
    private EmailService emailService;
    private Booking booking;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
        booking = Mockito.mock(Booking.class);
    }

    /**
     * Test sending confirmation with valid booking details.
     */
    @Test
    void testSendConfirmation_NormalCase() {
        // Arrange
        Mockito.when(booking.getBookingReference()).thenReturn("REF123");
        Mockito.when(booking.getPassengerDetails()).thenReturn(Collections.singletonList(new PassengerDetails()));
        // Act & Assert
        Assertions.assertDoesNotThrow(() -> emailService.sendConfirmation(booking));
    }

    /**
     * Test sending confirmation with null booking (edge case).
     */
    @Test
    void testSendConfirmation_NullBooking() {
        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> emailService.sendConfirmation(null));
    }

    /**
     * Test sending confirmation with booking having null passenger details (boundary case).
     */
    @Test
    void testSendConfirmation_NullPassengerDetails() {
        // Arrange
        Mockito.when(booking.getBookingReference()).thenReturn("REF456");
        Mockito.when(booking.getPassengerDetails()).thenReturn(null);
        // Act & Assert
        Assertions.assertDoesNotThrow(() -> emailService.sendConfirmation(booking));
    }

    /**
     * Test sending confirmation with booking having empty passenger details list.
     */
    @Test
    void testSendConfirmation_EmptyPassengerDetails() {
        // Arrange
        Mockito.when(booking.getBookingReference()).thenReturn("REF789");
        Mockito.when(booking.getPassengerDetails()).thenReturn(Collections.emptyList());
        // Act & Assert
        Assertions.assertDoesNotThrow(() -> emailService.sendConfirmation(booking));
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
