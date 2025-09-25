package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.time.LocalDate;

/**
 * BookingService handles booking, seat validation, payment, and confirmation logic.
 */
@Service
public class BookingService {
    @Autowired
    private AirlineIntegrationService airlineIntegrationService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AuditLoggingService auditLoggingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Books a flight for a user, validates seat availability and processes payment.
     * @param request booking request
     * @param authentication OAuth2 authentication token
     * @return booking confirmation
     */
    @Transactional
    public BookingConfirmation bookFlight(BookingRequest request, JwtAuthenticationToken authentication) {
        // Validate seat availability
        boolean seatsAvailable = airlineIntegrationService.checkSeatAvailability(request.getFlightId());
        if (!seatsAvailable) {
            throw new NoSeatsAvailableException("No seats available for selected flight.");
        }
        // Process payment
        Payment payment = paymentService.processPayment(request.getPaymentInfo(), request.getFlightId());
        if (!"CONFIRMED".equals(payment.getStatus())) {
            throw new PaymentFailedException("Payment processing failed.");
        }
        // Confirm booking with airline
        Booking booking = airlineIntegrationService.confirmBooking(request, authentication.getName());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDate.now());
        bookingRepository.save(booking);
        payment.setBookingId(booking.getId());
        paymentRepository.save(payment);
        // Audit log
        auditLoggingService.logTransaction(booking, payment, authentication.getName());
        // Prepare confirmation
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(booking.getId());
        confirmation.setStatus(booking.getStatus());
        confirmation.setDetails(booking);
        return confirmation;
    }
}
