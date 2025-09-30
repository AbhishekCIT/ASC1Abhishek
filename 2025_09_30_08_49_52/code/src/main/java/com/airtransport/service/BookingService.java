package com.airtransport.service;

import com.airtransport.entity.Booking;
import com.airtransport.entity.Flight;
import com.airtransport.entity.Passenger;
import com.airtransport.model.*;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.FlightRepository;
import com.airtransport.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for booking business logic and validation.
 */
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                         FlightRepository flightRepository,
                         PassengerRepository passengerRepository,
                         PaymentService paymentService,
                         NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    /**
     * Book a flight with validations and payment processing.
     * @param request booking request
     * @return booking confirmation
     */
    @Transactional
    public BookFlightResponse bookFlight(BookFlightRequest request) {
        // Validate flight existence
        Optional<Flight> flightOpt = flightRepository.findById(request.getFlightId());
        if (flightOpt.isEmpty()) {
            throw new IllegalArgumentException("Selected flight is no longer available");
        }
        Flight flight = flightOpt.get();
        // Validate passenger
        PassengerDTO passengerDTO = request.getPassenger();
        Passenger passenger = new Passenger();
        passenger.setPassengerId(UUID.randomUUID().toString());
        passenger.setName(passengerDTO.getName());
        passenger.setEmail(passengerDTO.getEmail());
        passengerRepository.save(passenger);
        // Validate payment
        boolean paymentSuccess = paymentService.processPayment(request.getPayment(), flight.getPrice());
        if (!paymentSuccess) {
            throw new IllegalArgumentException("Payment details invalid or not authorized");
        }
        // Create booking
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(LocalDateTime.now());
        bookingRepository.save(booking);
        // Send confirmation
        notificationService.sendConfirmation(passenger.getEmail(), booking.getBookingId());
        // Build response
        BookFlightResponse response = new BookFlightResponse();
        response.setBookingId(booking.getBookingId());
        response.setStatus("CONFIRMED");
        BookFlightResponse.Confirmation confirmation = new BookFlightResponse.Confirmation();
        confirmation.setEmail(true);
        confirmation.setSms(true); // Assume SMS sent for demo
        response.setConfirmation(confirmation);
        return response;
    }

    /**
     * Get booking details by booking ID.
     * @param bookingId booking ID
     * @return booking details
     */
    public BookingDTO getBookingById(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking ID does not exist"));
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setStatus(booking.getStatus());
        dto.setCreatedAt(booking.getCreatedAt());
        // Map flight
        Flight flight = booking.getFlight();
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightId(flight.getFlightId());
        flightDTO.setAirline(flight.getAirline());
        flightDTO.setDeparture(flight.getDeparture());
        flightDTO.setArrival(flight.getArrival());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setFlightClass(flight.getFlightClass());
        dto.setFlight(flightDTO);
        // Map passenger
        Passenger passenger = booking.getPassenger();
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setPassengerId(passenger.getPassengerId());
        passengerDTO.setName(passenger.getName());
        passengerDTO.setEmail(passenger.getEmail());
        dto.setPassenger(passengerDTO);
        return dto;
    }
}
