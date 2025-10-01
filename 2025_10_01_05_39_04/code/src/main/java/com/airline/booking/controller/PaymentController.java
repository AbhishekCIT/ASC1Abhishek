package com.airline.booking.controller;

import com.airline.booking.model.Booking;
import com.airline.booking.model.Payment;
import com.airline.booking.model.Ticket;
import com.airline.booking.repository.BookingRepository;
import com.airline.booking.repository.TicketRepository;
import com.airline.booking.service.BookingService;
import com.airline.booking.service.NotificationService;
import com.airline.booking.service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Controller for payment processing API.
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private final TicketRepository ticketRepository;
    private final NotificationService notificationService;

    /**
     * Process payment for a booking.
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(
                request.getBookingId(),
                request.getAmount(),
                request.getMethod()
        );
        if ("SUCCESS".equals(payment.getStatus())) {
            Booking booking = bookingRepository.findById(request.getBookingId())
                    .orElseThrow(() -> new RuntimeException("Booking not found"));
            bookingService.confirmBooking(booking);
            // Issue ticket
            Ticket ticket = new Ticket();
            ticket.setBooking(booking);
            ticket.setTicketUrl("https://tickets.example.com/" + booking.getId());
            ticket.setIssuedAt(LocalDateTime.now());
            ticketRepository.save(ticket);
            // Send confirmation email
            notificationService.sendBookingConfirmation(
                    booking.getPassenger().getContact(),
                    "Booking Confirmation",
                    "Your booking is confirmed. Ticket: " + ticket.getTicketUrl()
            );
        }
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setStatus(payment.getStatus());
        return ResponseEntity.ok(response);
    }

    @Data
    public static class PaymentRequest {
        private String bookingId;
        private BigDecimal amount;
        private String method;
        private Object details;
    }

    @Data
    public static class PaymentResponse {
        private String paymentId;
        private String status;
    }
}
