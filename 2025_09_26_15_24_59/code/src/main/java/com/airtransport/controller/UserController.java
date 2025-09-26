package com.airtransport.controller;

import com.airtransport.model.BookingRequest;
import com.airtransport.model.BookingResponse;
import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.service.BookingService;
import com.airtransport.service.FlightSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle user requests for searching and booking flights.
 */
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@Validated
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;

    /**
     * Search for available flights based on criteria.
     * @param request Flight search criteria
     * @return List of available flights
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        logger.debug("Received flight search request: {}", request);
        FlightSearchResponse response = flightSearchService.searchFlights(request);
        logger.info("Flight search successful for {} to {}", request.getOrigin(), request.getDestination());
        return ResponseEntity.ok(response);
    }

    /**
     * Book a flight for the user.
     * @param request Booking request data
     * @return Booking confirmation or error
     */
    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request) {
        logger.debug("Received booking request: {}", request);
        BookingResponse response = bookingService.bookFlight(request);
        logger.info("Booking {} status: {}", response.getBookingRef(), response.getStatus());
        return ResponseEntity.ok(response);
    }
}
