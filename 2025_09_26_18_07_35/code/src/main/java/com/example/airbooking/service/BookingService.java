package com.example.airbooking.service;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.model.BookingRequest;
import com.example.airbooking.model.BookingResponse;
import com.example.airbooking.model.PaymentResult;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.PaymentRepository;
import com.example.airbooking.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing bookings.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ValidationUtils validationUtils;

    /**
     * Creates a new booking.
     * @param request BookingRequest
     * @return BookingResponse
     */
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        validationUtils.validateBookingRequest(request);
        Optional<Flight> flightOpt = flightRepository.findById(request.getFlightId());
        if (!flightOpt.isPresent()) {
            BookingResponse response = new BookingResponse();
            response.setStatus("FAILED");
            response.setError("Flight not found");
            return response;
        }
        Flight flight = flightOpt.get();
        // Process payment
        PaymentResult paymentResult = paymentService.processPayment(request.getPaymentInfo());
        if (!paymentResult.isSuccess()) {
            BookingResponse response = new BookingResponse();
            response.setStatus("FAILED");
            response.setError(paymentResult.getErrorMessage());
            return response;
        }
        // Save booking
        Booking booking = new Booking();
        booking.setBookingRef(UUID.randomUUID().toString());
        booking.setUserId(request.getUserId());
        booking.setFlightId(request.getFlightId());
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Save payment
        Payment payment = new Payment();
        payment.setPaymentId(paymentResult.getPaymentId());
        payment.setBookingRef(booking.getBookingRef());
        payment.setAmount(request.getPaymentInfo().getAmount());
        payment.setStatus("CONFIRMED");
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        // Send confirmation email
        emailService.sendConfirmationEmail(booking);
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setBookingRef(booking.getBookingRef());
        response.setStatus("CONFIRMED");
        response.setEmailSent(true);
        return response;
    }
}
