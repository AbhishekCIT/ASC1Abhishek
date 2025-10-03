package com.example.airbooking.controller;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Passenger;
import com.example.airbooking.entity.User;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for booking management APIs.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingManagementController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

    /**
     * List bookings for authenticated user
     */
    @GetMapping
    public ResponseEntity<?> listBookings(@RequestHeader("Authorization") String token) {
        User user = userService.authenticate(token);
        List<Booking> bookings = bookingService.listBookings(user.getUserId());
        return ResponseEntity.ok(Map.of("bookings", bookings));
    }

    /**
     * View booking details
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingDetails(@RequestHeader("Authorization") String token,
                                               @PathVariable String bookingId) {
        User user = userService.authenticate(token);
        Booking booking = bookingService.getBookingDetails(bookingId, user.getUserId());
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        boolean refundEligible = bookingService.isRefundEligible(booking);
        return ResponseEntity.ok(Map.of(
            "bookingId", booking.getBookingId(),
            "flightId", booking.getFlight().getFlightId(),
            "status", booking.getStatus(),
            "date", booking.getBookingDate(),
            "passengerDetails", booking.getPassengers(),
            "refundEligible", refundEligible
        ));
    }

    /**
     * Modify booking
     */
    @PutMapping("/{bookingId}")
    public ResponseEntity<?> modifyBooking(@RequestHeader("Authorization") String token,
                                           @PathVariable String bookingId,
                                           @RequestBody Map<String, Object> request) {
        User user = userService.authenticate(token);
        String newDate = (String) request.get("newDate");
        List<Map<String, String>> passengerDetailsList = (List<Map<String, String>>) request.get("passengerDetails");
        List<Passenger> passengers = new java.util.ArrayList<>();
        for (Map<String, String> pd : passengerDetailsList) {
            Passenger passenger = new Passenger();
            passenger.setName(pd.get("name"));
            passenger.setPassportNumber(pd.get("passportNumber"));
            passenger.setNationality(pd.get("nationality"));
            passengers.add(passenger);
        }
        Booking booking = bookingService.modifyBooking(bookingId, newDate, passengers, user.getUserId());
        return ResponseEntity.ok(Map.of(
            "bookingId", booking.getBookingId(),
            "status", booking.getStatus()
        ));
    }

    /**
     * Cancel booking
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> cancelBooking(@RequestHeader("Authorization") String token,
                                           @PathVariable String bookingId) {
        User user = userService.authenticate(token);
        Map<String, Object> result = bookingService.cancelBooking(bookingId, user.getUserId());
        return ResponseEntity.ok(result);
    }
}
