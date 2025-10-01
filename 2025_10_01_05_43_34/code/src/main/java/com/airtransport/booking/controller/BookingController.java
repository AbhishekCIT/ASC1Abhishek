package com.airtransport.booking.controller;

import com.airtransport.booking.entity.Booking;
import com.airtransport.booking.model.BookingRequest;
import com.airtransport.booking.model.BookingResponse;
import com.airtransport.booking.model.BookingConfirmationRequest;
import com.airtransport.booking.model.BookingConfirmationResponse;
import com.airtransport.booking.service.BookingService;
import com.airtransport.booking.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

/**
 * Controller for booking creation and confirmation APIs.
 */
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final EmailService emailService;

    /**
     * Book a flight.
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingResponse> bookFlight(@Valid @RequestBody BookingRequest request, Principal principal) {
        Booking booking = bookingService.createBooking(principal.getName(), request);
        BookingResponse response = new BookingResponse();
        response.setBookingRef(booking.getId());
        response.setStatus(booking.getStatus());
        return ResponseEntity.ok(response);
    }

    /**
     * Get booking confirmation and e-ticket.
     */
    @GetMapping("/confirmation")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingConfirmationResponse> getConfirmation(@Valid @RequestBody BookingConfirmationRequest request, Principal principal) {
        Booking booking = bookingService.confirmBooking(request.getBookingRef());
        // Simulate e-ticket generation
        String eTicket = "ETKT-" + booking.getId();
        emailService.sendBookingConfirmation(booking.getUser().getEmail(), booking.getId(), eTicket);
        BookingConfirmationResponse response = new BookingConfirmationResponse();
        response.setStatus(booking.getStatus());
        response.setETicket(eTicket);
        return ResponseEntity.ok(response);
    }
}
