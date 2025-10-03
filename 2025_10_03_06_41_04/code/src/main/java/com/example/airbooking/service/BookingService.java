package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.PassengerRepository;
import com.example.airbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for managing booking logic, seat reservation, and payment coordination.
 */
@Service
public class BookingService {
    @Autowired
    private FlightInventoryService flightInventoryService;
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Create a booking, reserve seats, process payment, and send confirmation.
     */
    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) throws Exception {
        // Validate flight and seat availability
        boolean reserved = flightInventoryService.reserveSeats(
                bookingRequest.getFlightId(),
                bookingRequest.getPassengerDetails().size()
        );
        if (!reserved) {
            throw new Exception("Flight not available");
        }
        // Process payment
        Payment payment = paymentGatewayService.processPayment(bookingRequest.getPaymentInfo());
        if (!"SUCCESS".equals(payment.getPaymentStatus())) {
            throw new Exception("Payment authorization failed");
        }
        // Create booking
        Booking booking = new Booking();
        booking.setFlight(flightRepository.findById(bookingRequest.getFlightId()).orElse(null));
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingRef(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking = bookingRepository.save(booking);
        // Save passengers
        for (Passenger p : bookingRequest.getPassengerDetails()) {
            p.setBooking(booking);
            passengerRepository.save(p);
        }
        // Link payment
        payment.setBooking(booking);
        paymentRepository.save(payment);
        // Send notification
        notificationService.sendConfirmation(booking);
        return booking;
    }
}
