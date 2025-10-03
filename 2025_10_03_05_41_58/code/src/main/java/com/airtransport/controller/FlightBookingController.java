package com.airtransport.controller;

import com.airtransport.model.*;
import com.airtransport.entity.Flight;
import com.airtransport.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller exposing APIs for flight search, booking, payment, and confirmation.
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
     * @param request Flight search parameters
     * @return List of available flights
     */
    @GetMapping("/flights/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Validated FlightSearchRequest request) {
        List<Flight> flights = flightSearchService.searchFlights(request.getDate(), request.getDestination(), request.getAirline());
        List<FlightSearchResponse.FlightDto> flightDtos = flights.stream().map(f ->
                new FlightSearchResponse.FlightDto(
                        f.getFlightId(),
                        f.getAirline(),
                        f.getOrigin(),
                        f.getDestination(),
                        f.getDeparture().toString(),
                        f.getArrival().toString(),
                        f.getPrice().doubleValue()
                )
        ).collect(Collectors.toList());
        return ResponseEntity.ok(new FlightSearchResponse(flightDtos));
    }

    /**
     * Book a flight.
     * @param request Booking request
     * @return Booking response
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookFlight(@Validated @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookFlight(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Process payment for a booking.
     * @param request Payment request
     * @return Payment response
     */
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> processPayment(@Validated @RequestBody PaymentRequest request) {
        String txnId = paymentService.processPayment(request.getBookingId(), request.getAmount(), request.getPaymentMethod());
        return ResponseEntity.ok(new PaymentResponse("SUCCESS", txnId));
    }

    /**
     * Get booking confirmation.
     * @param bookingId Booking identifier
     * @return Booking confirmation response
     */
    @GetMapping("/bookings/{bookingId}/confirmation")
    public ResponseEntity<BookingConfirmationResponse> getBookingConfirmation(@PathVariable String bookingId) {
        // For demo, just return a mocked response (should fetch from DB in real app)
        BookingConfirmationResponse response = new BookingConfirmationResponse(bookingId, "CONFIRMED", null);
        return ResponseEntity.ok(response);
    }
}
