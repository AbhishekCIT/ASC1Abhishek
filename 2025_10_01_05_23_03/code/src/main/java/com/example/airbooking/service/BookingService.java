package com.example.airbooking.service;

import com.example.airbooking.dto.BookingRequest;
import com.example.airbooking.dto.BookingResponse;
import com.example.airbooking.entity.*;
import com.example.airbooking.exception.*;
import com.example.airbooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for booking flights and handling booking logic.
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
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    /**
     * Creates a new booking after validating input and processing payment.
     * @param request Booking request
     * @return Booking response
     */
    public BookingResponse createBooking(BookingRequest request) {
        // Validate input
        if (request.getFlightId() == null) {
            throw new InvalidInputException("FlightId is required");
        }
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));
        // Validate passenger info
        Map<String, Object> passengerInfo = request.getPassengerInfo();
        if (passengerInfo == null || !passengerInfo.containsKey("email")) {
            throw new InvalidInputException("Passenger email is required");
        }
        String passengerEmail = (String) passengerInfo.get("email");
        Passenger passenger = passengerRepository.findByEmail(passengerEmail);
        if (passenger == null) {
            passenger = new Passenger();
            passenger.setName((String) passengerInfo.getOrDefault("name", ""));
            passenger.setEmail(passengerEmail);
            passenger = passengerRepository.save(passenger);
        }
        // Validate travel date
        if (flight.getDepartureDate().isBefore(LocalDate.now())) {
            throw new InvalidInputException("Invalid or past travel date");
        }
        // Calculate fare (including taxes/fees)
        BigDecimal totalFare = flight.getPrice().add(new BigDecimal("20.00")); // Example fee
        // Process payment
        Map<String, Object> paymentInfo = request.getPaymentInfo();
        Payment payment = paymentService.processPayment(paymentInfo, totalFare);
        if (!"CONFIRMED".equals(payment.getStatus())) {
            throw new PaymentFailedException("Payment authorization failed");
        }
        // Save booking
        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setPayment(payment);
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(LocalDateTime.now());
        booking = bookingRepository.save(booking);
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setStatus(booking.getStatus());
        response.setTotalFare(totalFare);
        Map<String, Object> flightDetails = new HashMap<>();
        flightDetails.put("flightId", flight.getId());
        flightDetails.put("origin", flight.getOrigin());
        flightDetails.put("destination", flight.getDestination());
        flightDetails.put("departureDate", flight.getDepartureDate());
        response.setFlightDetails(flightDetails);
        return response;
    }

    /**
     * Retrieves booking details by booking ID.
     * @param bookingId Booking ID
     * @return Booking response
     */
    public BookingResponse getBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setStatus(booking.getStatus());
        response.setTotalFare(booking.getPayment().getAmount());
        Map<String, Object> flightDetails = new HashMap<>();
        Flight flight = booking.getFlight();
        flightDetails.put("flightId", flight.getId());
        flightDetails.put("origin", flight.getOrigin());
        flightDetails.put("destination", flight.getDestination());
        flightDetails.put("departureDate", flight.getDepartureDate());
        response.setFlightDetails(flightDetails);
        return response;
    }
}
