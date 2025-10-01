package com.airline.booking.controller;

import com.airline.booking.model.Booking;
import com.airline.booking.service.BookingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controller for booking and seat selection APIs.
 */
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    /**
     * Book a ticket (create booking draft).
     */
    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(@Valid @RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(
                request.getFlightId(),
                request.getPassenger().getName(),
                request.getPassenger().getContact(),
                request.getSeat()
        );
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setStatus(booking.getStatus());
        response.setTicketUrl(null); // Ticket URL will be set after payment
        return ResponseEntity.ok(response);
    }

    @Data
    public static class BookingRequest {
        private String flightId;
        private PassengerRequest passenger;
        private String seat;
        private PaymentRequest payment;
    }

    @Data
    public static class PassengerRequest {
        private String name;
        private String contact;
    }

    @Data
    public static class PaymentRequest {
        private String method;
        private Object details;
    }

    @Data
    public static class BookingResponse {
        private String bookingId;
        private String status;
        private String ticketUrl;
    }
}
