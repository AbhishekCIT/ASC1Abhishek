package com.example.airtransport.controller;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling booking creation and confirmation.
 */
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    /**
     * Creates a new booking for a flight.
     * @param request Booking request
     * @return BookingResponse
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Confirms a booking by bookingId.
     * @param bookingId Booking ID
     * @return BookingResponse
     */
    @GetMapping("/{bookingId}/confirm")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingResponse> confirmBooking(@PathVariable String bookingId) {
        BookingResponse response = bookingService.confirmBooking(bookingId);
        return ResponseEntity.ok(response);
    }
}
