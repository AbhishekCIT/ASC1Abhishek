package com.airbook.controller;

import com.airbook.model.Booking;
import com.airbook.model.Refund;
import com.airbook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for booking management operations
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Get all bookings for a user
     * @param userId User ID
     * @return List of bookings
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(@RequestParam String userId) {
        List<Booking> bookings = bookingService.getBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    /**
     * Modify a booking
     * @param bookingId Booking ID
     * @param newDate New travel date
     * @param seat New seat
     * @return Modified booking
     */
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> modifyBooking(
            @PathVariable String bookingId,
            @RequestParam String newDate,
            @RequestParam String seat) {
        Booking modifiedBooking = bookingService.modifyBooking(bookingId, newDate, seat);
        return ResponseEntity.ok(modifiedBooking);
    }

    /**
     * Cancel a booking
     * @param bookingId Booking ID
     * @return Refund details
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Refund> cancelBooking(@PathVariable String bookingId) {
        Refund refund = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(refund);
    }

    /**
     * Get booking history for a user
     * @param userId User ID
     * @return List of past bookings
     */
    @GetMapping("/history")
    public ResponseEntity<List<Booking>> getBookingHistory(@RequestParam String userId) {
        List<Booking> history = bookingService.getBookingHistory(userId);
        return ResponseEntity.ok(history);
    }
}
