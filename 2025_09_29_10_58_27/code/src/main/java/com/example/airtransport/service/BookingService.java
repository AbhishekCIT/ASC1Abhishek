package com.example.airtransport.service;

import com.example.airtransport.model.*;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.repository.FlightRepository;
import com.example.airtransport.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage booking creation, validation, and persistence.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    /**
     * Book a flight: validate, persist, process payment, send notification.
     */
    @Transactional
    public BookingResponse bookFlight(BookingRequest request) {
        // Validate user
        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ValidationException("Invalid user ID"));
        // Validate flight
        var flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new ValidationException("Invalid flight ID"));
        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setPassengerInfo(request.getPassengerInfo());
        booking.setStatus("PENDING");
        booking = bookingRepository.save(booking);
        // Process payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBookingId(booking.getId());
        paymentRequest.setPaymentInfo(request.getPaymentInfo());
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        if (!"SUCCESS".equals(paymentResponse.getStatus())) {
            throw new ValidationException("Payment failed");
        }
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Send e-ticket
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setBookingId(booking.getId());
        emailRequest.setEmail(user.getEmail());
        EmailResponse emailResponse = notificationService.sendBookingConfirmationEmail(emailRequest);
        // Build response
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setStatus(booking.getStatus());
        response.setETicket(emailResponse.getETicketUrl());
        return response;
    }
}
