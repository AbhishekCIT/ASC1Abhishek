package com.example.airlinebooking.service;

import com.example.airlinebooking.entity.*;
import com.example.airlinebooking.exception.SeatUnavailableException;
import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.model.BookingResponse;
import com.example.airlinebooking.repository.BookingRepository;
import com.example.airlinebooking.repository.FlightRepository;
import com.example.airlinebooking.repository.PassengerRepository;
import com.example.airlinebooking.util.BookingReferenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for booking-related business logic.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightService flightService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BookingReferenceUtil bookingReferenceUtil;

    /**
     * Creates a new booking, checks seat availability, processes payment, and sends notification.
     * @param request booking request
     * @return booking response
     */
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        // Validate seat availability
        if (!flightService.checkSeatAvailability(request.getFlightId(), request.getPassengerDetails().size())) {
            throw new SeatUnavailableException("Selected seat not available");
        }
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new SeatUnavailableException("Flight not found"));
        // Decrement seat count
        flightService.decrementSeats(request.getFlightId(), request.getPassengerDetails().size());
        // Create booking
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingReference(bookingReferenceUtil.generateBookingReference());
        booking.setStatus("PENDING");
        List<Passenger> passengers = new ArrayList<>();
        for (BookingRequest.PassengerDetails pd : request.getPassengerDetails()) {
            Passenger passenger = new Passenger();
            passenger.setName(pd.getName());
            passenger.setEmail(pd.getEmail());
            passenger.setPhone(pd.getPhone());
            passenger.setBooking(booking);
            passengers.add(passenger);
        }
        booking.setPassengers(passengers);
        bookingRepository.save(booking);
        // Process payment
        paymentService.processPayment(booking, request.getPaymentDetails());
        // Update booking status
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Send confirmation notification
        notificationService.sendConfirmation(booking);
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setBookingReference(booking.getBookingReference());
        response.setStatus(booking.getStatus());
        return response;
    }
}
