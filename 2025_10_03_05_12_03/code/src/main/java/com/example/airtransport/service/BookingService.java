package com.example.airtransport.service;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.model.PaymentInfo;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.repository.FlightRepository;
import com.example.airtransport.util.ValidationUtil;
import com.example.airtransport.exception.PaymentFailedException;
import com.example.airtransport.exception.SeatUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for booking logic and seat locking.
 */
@Service
public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Books a flight, processes payment, saves booking, and sends confirmation email.
     * @param request Booking request
     * @return Booking confirmation response
     */
    public BookingResponse bookFlight(BookingRequest request) {
        // Lock seats
        boolean locked = flightRepository.lockSeats(request.getFlightId(), request.getSeats());
        if (!locked) {
            throw new SeatUnavailableException("Selected seats are no longer available.");
        }
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(request.getPaymentInfo());
        if (!paymentSuccess) {
            throw new PaymentFailedException("Payment could not be processed.");
        }
        // Save booking
        BookingResponse response = bookingRepository.saveBooking(request);
        // Send confirmation email
        emailService.sendConfirmationEmail(response);
        return response;
    }
}
