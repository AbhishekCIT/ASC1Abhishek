package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller for flight search and booking operations.
 */
@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightSearchService flightSearchService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

    /**
     * Search for available flights by origin, destination, and date.
     * @param request FlightSearchRequest
     * @return List of available flights
     */
    @GetMapping("/flights/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(@Valid FlightSearchRequest request) {
        List<FlightResponse> flights = flightSearchService.searchFlights(request);
        return ResponseEntity.ok(flights);
    }

    /**
     * Create a new booking for a flight.
     * @param request BookingRequest
     * @return BookingResponse
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Process payment for a booking.
     * @param request PaymentRequest
     * @return PaymentResponse
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get booking confirmation and e-ticket.
     * @param bookingId Booking ID
     * @return BookingResponse
     */
    @GetMapping("/bookings/{bookingId}/confirm")
    public ResponseEntity<BookingResponse> getBookingConfirmation(@PathVariable String bookingId) {
        BookingResponse response = bookingService.getBookingConfirmation(bookingId);
        return ResponseEntity.ok(response);
    }
}
