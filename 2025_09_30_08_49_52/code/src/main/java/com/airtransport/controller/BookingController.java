package com.airtransport.controller;

import com.airtransport.model.BookFlightRequest;
import com.airtransport.model.BookFlightResponse;
import com.airtransport.model.BookingDTO;
import com.airtransport.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for booking flights and retrieving bookings.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Book a flight.
     * @param request booking request
     * @return booking confirmation
     */
    @PostMapping
    public ResponseEntity<BookFlightResponse> bookFlight(@RequestBody BookFlightRequest request) {
        BookFlightResponse response = bookingService.bookFlight(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get booking details by booking ID.
     * @param bookingId booking ID
     * @return booking details
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable String bookingId) {
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }
}
