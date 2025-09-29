package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * REST controller for flight search and booking APIs.
 */
@RestController
@RequestMapping("/api")
public class FlightController {
    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;

    @Autowired
    public FlightController(FlightSearchService flightSearchService, BookingService bookingService) {
        this.flightSearchService = flightSearchService;
        this.bookingService = bookingService;
    }

    /**
     * Search for available flights by origin, destination, and date.
     * @param params request body with origin, destination, date
     * @return list of available flights
     */
    @GetMapping("/flights/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam Map<String, String> params) {
        String origin = params.get("origin");
        String destination = params.get("destination");
        String dateStr = params.get("date");
        LocalDate date = LocalDate.parse(dateStr);
        List<Flight> flights = flightSearchService.searchFlights(origin, destination, date);
        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(flights);
        }
        return ResponseEntity.ok(flights);
    }

    /**
     * Book a ticket for a flight.
     * @param bookingRequest booking request details
     * @return booking response
     */
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookTicket(@RequestBody BookingRequest bookingRequest) {
        try {
            BookingResponse response = bookingService.createBooking(
                    bookingRequest.getFlightId(),
                    bookingRequest.getPassengerDetails(),
                    bookingRequest.getSeat(),
                    bookingRequest.getPaymentInfo()
            );
            if ("CONFIRMED".equals(response.getStatus())) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new BookingResponse(null, "FAILED", false, ex.getMessage()));
        }
    }
}
