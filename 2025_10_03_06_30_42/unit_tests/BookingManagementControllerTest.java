package com.example.airbooking.controller;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.Passenger;
import com.example.airbooking.entity.User;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.UserService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for BookingManagementController.
 * Covers normal, edge, boundary, and error scenarios for all controller methods.
 */
class BookingManagementControllerTest {
    @Mock
    private BookingService bookingService;
    @Mock
    private UserService userService;
    @InjectMocks
    private BookingManagementController controller;
    private AutoCloseable closeable;

    private User mockUser;
    private Booking mockBooking;
    private Flight mockFlight;
    private List<Passenger> mockPassengers;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockUser = new User();
        mockUser.setUserId("user123");
        mockUser.setEmail("test@example.com");
        mockBooking = new Booking();
        mockBooking.setBookingId("booking123");
        mockBooking.setStatus("CONFIRMED");
        mockBooking.setBookingDate(LocalDateTime.now());
        mockFlight = new Flight();
        mockFlight.setFlightId("flight456");
        mockBooking.setFlight(mockFlight);
        mockPassengers = new ArrayList<>();
        Passenger p = new Passenger();
        p.setName("John Doe");
        p.setPassportNumber("A1234567");
        p.setNationality("US");
        mockPassengers.add(p);
        mockBooking.setPassengers(mockPassengers);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test listing bookings for a valid user (normal scenario).
     */
    @Test
    void testListBookings_Normal() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        when(bookingService.listBookings("user123")).thenReturn(List.of(mockBooking));
        ResponseEntity<?> response = controller.listBookings("Bearer token");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertTrue(body.containsKey("bookings"));
        assertEquals(1, ((List<?>)body.get("bookings")).size());
    }

    /**
     * Test listing bookings for a user with no bookings (edge case).
     */
    @Test
    void testListBookings_Empty() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        when(bookingService.listBookings("user123")).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = controller.listBookings("Bearer token");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertTrue(body.containsKey("bookings"));
        assertTrue(((List<?>)body.get("bookings")).isEmpty());
    }

    /**
     * Test getBookingDetails for valid booking (normal scenario).
     */
    @Test
    void testGetBookingDetails_Normal() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        when(bookingService.getBookingDetails("booking123", "user123")).thenReturn(mockBooking);
        when(bookingService.isRefundEligible(mockBooking)).thenReturn(true);
        ResponseEntity<?> response = controller.getBookingDetails("Bearer token", "booking123");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("booking123", body.get("bookingId"));
        assertEquals("flight456", body.get("flightId"));
        assertEquals("CONFIRMED", body.get("status"));
        assertTrue((Boolean)body.get("refundEligible"));
    }

    /**
     * Test getBookingDetails for non-existent booking (error scenario).
     */
    @Test
    void testGetBookingDetails_NotFound() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        when(bookingService.getBookingDetails("booking999", "user123")).thenReturn(null);
        ResponseEntity<?> response = controller.getBookingDetails("Bearer token", "booking999");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    /**
     * Test modifyBooking with valid data (normal scenario).
     */
    @Test
    void testModifyBooking_Normal() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        when(bookingService.modifyBooking(eq("booking123"), anyString(), anyList(), eq("user123"))).thenReturn(mockBooking);
        Map<String, Object> request = new HashMap<>();
        request.put("newDate", "2025-12-01");
        List<Map<String, String>> passengerDetails = new ArrayList<>();
        Map<String, String> pd = new HashMap<>();
        pd.put("name", "John Doe");
        pd.put("passportNumber", "A1234567");
        pd.put("nationality", "US");
        passengerDetails.add(pd);
        request.put("passengerDetails", passengerDetails);
        ResponseEntity<?> response = controller.modifyBooking("Bearer token", "booking123", request);
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("booking123", body.get("bookingId"));
        assertEquals("CONFIRMED", body.get("status"));
    }

    /**
     * Test modifyBooking with empty passenger list (edge case).
     */
    @Test
    void testModifyBooking_EmptyPassengers() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        Booking modifiedBooking = new Booking();
        modifiedBooking.setBookingId("booking123");
        modifiedBooking.setStatus("MODIFIED");
        when(bookingService.modifyBooking(eq("booking123"), anyString(), anyList(), eq("user123"))).thenReturn(modifiedBooking);
        Map<String, Object> request = new HashMap<>();
        request.put("newDate", "2025-12-01");
        request.put("passengerDetails", new ArrayList<>());
        ResponseEntity<?> response = controller.modifyBooking("Bearer token", "booking123", request);
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("booking123", body.get("bookingId"));
        assertEquals("MODIFIED", body.get("status"));
    }

    /**
     * Test cancelBooking for valid booking (normal scenario).
     */
    @Test
    void testCancelBooking_Normal() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        Map<String, Object> result = new HashMap<>();
        result.put("bookingId", "booking123");
        result.put("status", "CANCELLED");
        result.put("refund", Map.of("amount", 100, "status", "PENDING"));
        when(bookingService.cancelBooking("booking123", "user123")).thenReturn(result);
        ResponseEntity<?> response = controller.cancelBooking("Bearer token", "booking123");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("booking123", body.get("bookingId"));
        assertEquals("CANCELLED", body.get("status"));
        assertTrue(body.containsKey("refund"));
    }

    /**
     * Test cancelBooking for booking with no refund (boundary condition).
     */
    @Test
    void testCancelBooking_NoRefund() {
        when(userService.authenticate(anyString())).thenReturn(mockUser);
        Map<String, Object> result = new HashMap<>();
        result.put("bookingId", "booking123");
        result.put("status", "CANCELLED");
        result.put("refund", Map.of("amount", 0, "status", "NOT_ELIGIBLE"));
        when(bookingService.cancelBooking("booking123", "user123")).thenReturn(result);
        ResponseEntity<?> response = controller.cancelBooking("Bearer token", "booking123");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("booking123", body.get("bookingId"));
        assertEquals("CANCELLED", body.get("status"));
        Map<?,?> refund = (Map<?,?>) body.get("refund");
        assertEquals(0, refund.get("amount"));
        assertEquals("NOT_ELIGIBLE", refund.get("status"));
    }

    /**
     * Test authentication failure (error scenario).
     */
    @Test
    void testAuthenticationFailure() {
        when(userService.authenticate(anyString())).thenThrow(new RuntimeException("Invalid token"));
        assertThrows(RuntimeException.class, () -> controller.listBookings("bad token"));
        assertThrows(RuntimeException.class, () -> controller.getBookingDetails("bad token", "booking123"));
        assertThrows(RuntimeException.class, () -> controller.modifyBooking("bad token", "booking123", new HashMap<>()));
        assertThrows(RuntimeException.class, () -> controller.cancelBooking("bad token", "booking123"));
    }
}
