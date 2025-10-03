package com.example.airbooking.controller;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.Passenger;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.service.PaymentService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightController.
 * Covers normal, edge, boundary, and error scenarios for all controller methods.
 */
class FlightControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private FlightController controller;
    private AutoCloseable closeable;

    private Flight mockFlight;
    private Booking mockBooking;
    private Payment mockPayment;
    private List<Passenger> mockPassengers;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockFlight = new Flight();
        mockFlight.setFlightId("FL123");
        mockFlight.setOrigin("JFK");
        mockFlight.setDestination("LAX");
        mockFlight.setDate(LocalDate.of(2025, 12, 1));
        mockFlight.setPrice(new BigDecimal("350.00"));
        mockFlight.setSeatsAvailable(10);
        mockBooking = new Booking();
        mockBooking.setBookingId("BK456");
        mockBooking.setStatus("CONFIRMED");
        mockBooking.setEmail("user@example.com");
        mockBooking.setFlight(mockFlight);
        mockPassengers = new ArrayList<>();
        Passenger p = new Passenger();
        p.setName("Jane Doe");
        p.setPassportNumber("B9876543");
        p.setNationality("CA");
        mockPassengers.add(p);
        mockBooking.setPassengers(mockPassengers);
        mockPayment = new Payment();
        mockPayment.setPaymentId("PM789");
        mockPayment.setAmount(new BigDecimal("350.00"));
        mockPayment.setStatus("SUCCESS");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test searchFlights with valid parameters (normal scenario).
     */
    @Test
    void testSearchFlights_Normal() {
        when(flightSearchService.searchFlights("JFK", "LAX", LocalDate.of(2025, 12, 1)))
                .thenReturn(List.of(mockFlight));
        ResponseEntity<?> response = controller.searchFlights("JFK", "LAX", "2025-12-01");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertTrue(body.containsKey("flights"));
        assertEquals(1, ((List<?>)body.get("flights")).size());
    }

    /**
     * Test searchFlights with no results (edge case).
     */
    @Test
    void testSearchFlights_Empty() {
        when(flightSearchService.searchFlights(anyString(), anyString(), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());
        ResponseEntity<?> response = controller.searchFlights("JFK", "LAX", "2025-12-01");
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertTrue(body.containsKey("flights"));
        assertTrue(((List<?>)body.get("flights")).isEmpty());
    }

    /**
     * Test getFlightDetails for valid flight (normal scenario).
     */
    @Test
    void testGetFlightDetails_Normal() {
        when(flightSearchService.getFlightDetails("FL123")).thenReturn(mockFlight);
        ResponseEntity<?> response = controller.getFlightDetails("FL123");
        assertEquals(200, response.getStatusCodeValue());
        Flight flight = (Flight) response.getBody();
        assertEquals("FL123", flight.getFlightId());
    }

    /**
     * Test getFlightDetails for non-existent flight (error scenario).
     */
    @Test
    void testGetFlightDetails_NotFound() {
        when(flightSearchService.getFlightDetails("FL999")).thenReturn(null);
        ResponseEntity<?> response = controller.getFlightDetails("FL999");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    /**
     * Test bookTicket with valid data (normal scenario).
     */
    @Test
    void testBookTicket_Normal() {
        when(bookingService.bookFlight(eq("FL123"), anyList(), any(Payment.class), eq("user@example.com")))
                .thenReturn(mockBooking);
        Map<String, Object> request = new HashMap<>();
        request.put("flightId", "FL123");
        request.put("email", "user@example.com");
        List<Map<String, String>> passengerDetails = new ArrayList<>();
        Map<String, String> pd = new HashMap<>();
        pd.put("name", "Jane Doe");
        pd.put("passportNumber", "B9876543");
        pd.put("nationality", "CA");
        passengerDetails.add(pd);
        request.put("passengerDetails", passengerDetails);
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("amount", "350.00");
        request.put("paymentInfo", paymentInfo);
        ResponseEntity<?> response = controller.bookTicket(request);
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("BK456", body.get("bookingId"));
        assertEquals("CONFIRMED", body.get("status"));
        assertEquals("user@example.com", body.get("email"));
    }

    /**
     * Test bookTicket with empty passenger list (edge case).
     */
    @Test
    void testBookTicket_EmptyPassengers() {
        Booking booking = new Booking();
        booking.setBookingId("BK999");
        booking.setStatus("CONFIRMED");
        booking.setEmail("user@example.com");
        when(bookingService.bookFlight(eq("FL123"), anyList(), any(Payment.class), eq("user@example.com")))
                .thenReturn(booking);
        Map<String, Object> request = new HashMap<>();
        request.put("flightId", "FL123");
        request.put("email", "user@example.com");
        request.put("passengerDetails", new ArrayList<>());
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("amount", "350.00");
        request.put("paymentInfo", paymentInfo);
        ResponseEntity<?> response = controller.bookTicket(request);
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("BK999", body.get("bookingId"));
        assertEquals("CONFIRMED", body.get("status"));
    }

    /**
     * Test processPayment with valid data (normal scenario).
     */
    @Test
    void testProcessPayment_Normal() {
        when(paymentService.processPayment(any(Payment.class))).thenReturn(mockPayment);
        Map<String, Object> request = new HashMap<>();
        request.put("bookingId", "BK456");
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("amount", "350.00");
        request.put("paymentInfo", paymentInfo);
        ResponseEntity<?> response = controller.processPayment(request);
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("PM789", body.get("paymentId"));
        assertEquals("SUCCESS", body.get("status"));
    }

    /**
     * Test processPayment with zero amount (boundary condition).
     */
    @Test
    void testProcessPayment_ZeroAmount() {
        Payment payment = new Payment();
        payment.setPaymentId("PM000");
        payment.setAmount(BigDecimal.ZERO);
        payment.setStatus("SUCCESS");
        when(paymentService.processPayment(any(Payment.class))).thenReturn(payment);
        Map<String, Object> request = new HashMap<>();
        request.put("bookingId", "BK000");
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("amount", "0.00");
        request.put("paymentInfo", paymentInfo);
        ResponseEntity<?> response = controller.processPayment(request);
        assertEquals(200, response.getStatusCodeValue());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertEquals("PM000", body.get("paymentId"));
        assertEquals("SUCCESS", body.get("status"));
    }

    /**
     * Test searchFlights with invalid date format (error scenario).
     */
    @Test
    void testSearchFlights_InvalidDateFormat() {
        assertThrows(Exception.class, () -> controller.searchFlights("JFK", "LAX", "invalid-date"));
    }
}
