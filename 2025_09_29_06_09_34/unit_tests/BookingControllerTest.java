package com.airbook.controller;

import com.airbook.model.Booking;
import com.airbook.model.Refund;
import com.airbook.service.BookingService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingController
 */
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

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
     * Test getBookings returns bookings for valid user
     */
    @Test
    void testGetBookings_ValidUser_ReturnsBookings() {
        String userId = "user123";
        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());
        when(bookingService.getBookings(userId)).thenReturn(bookings);

        ResponseEntity<List<Booking>> response = bookingController.getBookings(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookings, response.getBody());
    }

    /**
     * Test getBookings with empty userId (edge case)
     */
    @Test
    void testGetBookings_EmptyUserId_ReturnsEmptyList() {
        String userId = "";
        when(bookingService.getBookings(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Booking>> response = bookingController.getBookings(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test modifyBooking returns modified booking for valid input
     */
    @Test
    void testModifyBooking_ValidInput_ReturnsModifiedBooking() {
        String bookingId = "b1";
        String newDate = "2025-12-01";
        String seat = "12A";
        Booking modified = new Booking();
        when(bookingService.modifyBooking(bookingId, newDate, seat)).thenReturn(modified);

        ResponseEntity<Booking> response = bookingController.modifyBooking(bookingId, newDate, seat);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(modified, response.getBody());
    }

    /**
     * Test modifyBooking with invalid bookingId (error scenario)
     */
    @Test
    void testModifyBooking_InvalidBookingId_ReturnsNull() {
        String bookingId = "invalid";
        String newDate = "2025-12-01";
        String seat = "12A";
        when(bookingService.modifyBooking(bookingId, newDate, seat)).thenReturn(null);

        ResponseEntity<Booking> response = bookingController.modifyBooking(bookingId, newDate, seat);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    /**
     * Test cancelBooking returns refund for valid bookingId
     */
    @Test
    void testCancelBooking_ValidBookingId_ReturnsRefund() {
        String bookingId = "b1";
        Refund refund = new Refund();
        when(bookingService.cancelBooking(bookingId)).thenReturn(refund);

        ResponseEntity<Refund> response = bookingController.cancelBooking(bookingId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(refund, response.getBody());
    }

    /**
     * Test cancelBooking with invalid bookingId (error scenario)
     */
    @Test
    void testCancelBooking_InvalidBookingId_ReturnsNull() {
        String bookingId = "invalid";
        when(bookingService.cancelBooking(bookingId)).thenReturn(null);

        ResponseEntity<Refund> response = bookingController.cancelBooking(bookingId);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    /**
     * Test getBookingHistory returns history for valid user
     */
    @Test
    void testGetBookingHistory_ValidUser_ReturnsHistory() {
        String userId = "user123";
        List<Booking> history = Arrays.asList(new Booking(), new Booking());
        when(bookingService.getBookingHistory(userId)).thenReturn(history);

        ResponseEntity<List<Booking>> response = bookingController.getBookingHistory(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(history, response.getBody());
    }

    /**
     * Test getBookingHistory with empty userId (edge case)
     */
    @Test
    void testGetBookingHistory_EmptyUserId_ReturnsEmptyList() {
        String userId = "";
        when(bookingService.getBookingHistory(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Booking>> response = bookingController.getBookingHistory(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }
}
