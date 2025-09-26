package com.airtransport.service;

import com.airtransport.model.*;
import com.airtransport.entity.*;
import com.airtransport.repository.BookingRepository;
import com.airtransport.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

/**
 * BookingService handles booking, payment, and notification logic.
 */
@Service
public class BookingService {
    @Autowired
    private AirlineAPIClient airlineAPIClient;
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Books a flight, processes payment, and sends confirmation.
     */
    @Transactional
    public BookingConfirmation bookFlight(BookingRequest request) {
        // Reserve seat with airline
        boolean seatReserved = airlineAPIClient.reserveSeat(request.getFlightId(), request.getSeatPreference());
        if (!seatReserved) {
            throw new SeatUnavailableException("Selected seat is no longer available");
        }
        // Process payment
        boolean paymentSuccess = paymentGatewayService.processPayment(request.getPaymentInfo());
        if (!paymentSuccess) {
            throw new PaymentFailedException("Payment declined");
        }
        // Save booking
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setFlightId(request.getFlightId());
        booking.setStatus("CONFIRMED");
        booking.setTicketNumber(generateTicketNumber());
        booking.setBookingDate(LocalDateTime.now());
        bookingRepository.save(booking);
        // Send confirmation
        boolean emailSent = notificationService.sendConfirmation(request.getUserId(), booking);
        // Return confirmation
        return new BookingConfirmation(booking.getId(), "CONFIRMED", booking.getTicketNumber(), emailSent);
    }

    /**
     * Get booking confirmation by bookingId.
     */
    public BookingConfirmation getConfirmation(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        boolean emailSent = notificationService.wasConfirmationSent(bookingId);
        return new BookingConfirmation(booking.getId(), booking.getStatus(), booking.getTicketNumber(), emailSent);
    }

    private String generateTicketNumber() {
        // Simple ticket number generation logic
        return "TKT" + System.currentTimeMillis();
    }
}
