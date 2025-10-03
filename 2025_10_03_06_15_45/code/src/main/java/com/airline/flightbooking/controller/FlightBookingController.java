package com.airline.flightbooking.controller;

import com.airline.flightbooking.dto.*;
import com.airline.flightbooking.model.*;
import com.airline.flightbooking.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST Controller for flight search, booking, and payment APIs.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FlightBookingController {
    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    /**
     * Search for available flights.
     * @param request FlightSearchRequest
     * @return FlightSearchResponse
     */
    @GetMapping("/flights/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        List<Flight> flights = flightSearchService.searchFlights(
                request.getOrigin(),
                request.getDestination(),
                LocalDate.parse(request.getDate())
        );
        return ResponseEntity.ok(new FlightSearchResponse(flights));
    }

    /**
     * Book a ticket for a flight.
     * @param request BookingRequest
     * @return BookingResponse
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookTicket(@Valid @RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request.getFlightId(), request.getPassenger());
        return ResponseEntity.ok(new BookingResponse(booking.getBookingId(), booking.getStatus(), booking));
    }

    /**
     * Process payment for a booking.
     * @param request PaymentRequest
     * @return PaymentResponse
     */
    @PostMapping("/payments/process")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(
                request.getBookingId(),
                request.getAmount(),
                request.getPaymentMethod()
        );
        // Confirm booking if payment is successful
        if ("SUCCESS".equals(payment.getStatus())) {
            bookingService.confirmBooking(request.getBookingId());
        }
        return ResponseEntity.ok(new PaymentResponse(payment.getPaymentId(), payment.getStatus(), payment));
    }
}
