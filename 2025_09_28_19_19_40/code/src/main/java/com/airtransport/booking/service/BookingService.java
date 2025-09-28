package com.airtransport.booking.service;

import com.airtransport.booking.entity.*;
import com.airtransport.booking.repository.BookingRepository;
import com.airtransport.booking.repository.FlightRepository;
import com.airtransport.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for booking logic and orchestration.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;

    /**
     * Creates a booking for the given flight, user, passenger info, and payment info.
     * @param flightId Flight ID
     * @param userId User ID
     * @param passengerInfo Passenger info (not persisted in this simple model)
     * @param paymentInfo Payment info
     * @return Booking
     */
    @Transactional
    public Booking createBooking(Long flightId, Long userId, Object passengerInfo, PaymentInfo paymentInfo) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (flightOpt.isEmpty()) {
            throw new RuntimeException("Selected flight no longer available");
        }
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Flight flight = flightOpt.get();
        User user = userOpt.get();
        // Process payment
        paymentInfo.setAmount(flight.getPrice());
        PaymentInfo processedPayment = paymentService.processPayment(paymentInfo);
        if (!"SUCCESS".equals(processedPayment.getStatus())) {
            throw new RuntimeException("Payment failed");
        }
        // Create booking
        Booking booking = new Booking();
        booking.setBookingRef(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalPrice(flight.getPrice());
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setPaymentInfo(processedPayment);
        Booking savedBooking = bookingRepository.save(booking);
        // Send confirmation email
        emailService.sendBookingConfirmation(savedBooking, user);
        return savedBooking;
    }

    /**
     * Retrieves a booking by reference.
     * @param bookingRef Booking reference
     * @return Booking
     */
    public Booking getBooking(String bookingRef) {
        return bookingRepository.findById(bookingRef)
                .orElseThrow(() -> new RuntimeException("Booking reference not found"));
    }
}
