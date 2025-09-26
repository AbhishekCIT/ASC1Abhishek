package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.BookingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller for managing bookings: view, modify, cancel.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingManagementController {

    @Autowired
    private BookingManagementService bookingManagementService;

    /**
     * View all bookings for a user.
     */
    @GetMapping
    public ResponseEntity<?> viewBookings(@RequestParam Long userId, @RequestHeader("Authorization") String token) {
        try {
            List<Booking> bookings = bookingManagementService.viewBookings(userId, token);
            return ResponseEntity.ok(new ViewBookingsResponse(bookings));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Modify a booking.
     */
    @PutMapping("/{bookingReference}")
    public ResponseEntity<?> modifyBooking(@PathVariable String bookingReference,
                                           @Valid @RequestBody BookingModificationRequest request,
                                           @RequestHeader("Authorization") String token) {
        try {
            BookingModificationResult result = bookingManagementService.modifyBooking(bookingReference, request, token);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Cancel a booking.
     */
    @DeleteMapping("/{bookingReference}")
    public ResponseEntity<?> cancelBooking(@PathVariable String bookingReference,
                                           @RequestHeader("Authorization") String token) {
        try {
            BookingCancellationResult result = bookingManagementService.cancelBooking(bookingReference, token);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
