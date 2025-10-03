package com.example.airbooking.service;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.Passenger;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.exception.SeatUnavailableException;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.PassengerRepository;
import com.example.airbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for managing bookings and validation.
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
    private FlightSearchService flightSearchService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;

    /**
     * Books a flight, validates seat availability, processes payment, and sends confirmation email.
     * @throws SeatUnavailableException
     */
    @Transactional
    public Booking bookFlight(String flightId, List<Passenger> passengerDetails, Payment paymentInfo, String email) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null || flight.getSeatsAvailable() < passengerDetails.size()) {
            throw new SeatUnavailableException("No seats available");
        }
        // Reduce seat count
        flight.setSeatsAvailable(flight.getSeatsAvailable() - passengerDetails.size());
        flightRepository.save(flight);

        // Create booking
        Booking booking = new Booking();
        booking.setBookingId(java.util.UUID.randomUUID().toString());
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setEmail(email);
        booking = bookingRepository.save(booking);

        // Save passengers
        for (Passenger passenger : passengerDetails) {
            passenger.setPassengerId(java.util.UUID.randomUUID().toString());
            passenger.setBooking(booking);
            passengerRepository.save(passenger);
        }
        booking.setPassengers(passengerDetails);

        // Process payment
        paymentInfo.setPaymentId(java.util.UUID.randomUUID().toString());
        paymentInfo.setBooking(booking);
        paymentInfo.setStatus("SUCCESS");
        paymentInfo.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(paymentInfo);
        booking.setPayment(paymentInfo);

        // Send confirmation email
        emailService.sendConfirmationEmail(booking);

        return booking;
    }
}
