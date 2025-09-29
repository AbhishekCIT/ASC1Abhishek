package com.example.booking.controller;

import com.example.booking.model.BookingRequest;
import com.example.booking.model.BookingResponse;
import com.example.booking.service.BookingService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for BookingController.
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

    /**
     * Test booking flight successfully (normal scenario).
     */
    @Test
    void testBookFlight_Success() {
        BookingRequest request = Mockito.mock(BookingRequest.class);
        BookingResponse response = Mockito.mock(BookingResponse.class);
        Mockito.when(bookingService.createBooking(request)).thenReturn(response);
        Mockito.when(response.getBookingReference()).thenReturn("BR123");

        ResponseEntity<?> entity = bookingController.bookFlight(request);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertTrue(entity.getBody() instanceof BookingResponse);
    }

    /**
     * Test booking flight with service throwing exception (error scenario).
     */
    @Test
    void testBookFlight_ServiceThrowsException() {
        BookingRequest request = Mockito.mock(BookingRequest.class);
        Mockito.when(bookingService.createBooking(request)).thenThrow(new RuntimeException("Booking failed"));

        ResponseEntity<?> entity = bookingController.bookFlight(request);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        Assertions.assertTrue(entity.getBody() instanceof BookingController.ErrorResponse);
        BookingController.ErrorResponse error = (BookingController.ErrorResponse) entity.getBody();
        Assertions.assertEquals("Booking failed", error.getError());
    }

    /**
     * Test booking flight with null request (edge case).
     */
    @Test
    void testBookFlight_NullRequest() {
        ResponseEntity<?> entity = bookingController.bookFlight(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        Assertions.assertTrue(entity.getBody() instanceof BookingController.ErrorResponse);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
