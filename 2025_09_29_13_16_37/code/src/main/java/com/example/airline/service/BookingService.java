package com.example.airline.service;

import com.example.airline.dto.BookingRequest;
import com.example.airline.dto.BookingResponse;
import com.example.airline.model.Booking;
import com.example.airline.model.Flight;
import com.example.airline.model.User;
import com.example.airline.repository.BookingRepository;
import com.example.airline.repository.FlightRepository;
import com.example.airline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
    private NotificationService notificationService;

    /**
     * Books a flight for a user, processes payment, and sends notification.
     * @param request Booking request
     * @return BookingResponse
     */
    @Transactional
    public BookingResponse bookFlight(BookingRequest request) {
        // Validate flight
        Optional<Flight> flightOpt = flightRepository.findById(request.getFlightId());
        if (!flightOpt.isPresent()) {
            throw new RuntimeException("Flight not available");
        }
        // Validate user
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        // TODO: Validate seat and price (real-time)
        // Process payment
        paymentService.processPayment(request);
        // Create booking
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setUser(userOpt.get());
        booking.setFlight(flightOpt.get());
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());
        bookingRepository.save(booking);
        // Send notification
        notificationService.sendBookingConfirmation(userOpt.get(), booking);
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setStatus(booking.getStatus());
        return response;
    }
}