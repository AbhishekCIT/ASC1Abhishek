package com.example.airtransport.service;

import com.example.airtransport.model.*;
import com.example.airtransport.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

/**
 * Service for orchestrating booking logic.
 */
@Service
public class BookingService {

    @Autowired
    private FlightInventoryService flightInventoryService;
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    @Autowired
    private BookingReferenceGenerator bookingReferenceGenerator;
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Book a flight: check seat, process payment, generate ref, save booking.
     */
    @Transactional
    public BookingResponse bookFlight(BookingRequest request) throws Exception {
        // Check seat availability
        if (!flightInventoryService.isSeatAvailable(request.getFlightId())) {
            throw new Exception("Seat unavailable");
        }
        // Process payment
        Payment payment = paymentGatewayService.processPayment(request.getPaymentInfo());
        if (!"SUCCESS".equals(payment.getStatus())) {
            throw new Exception("Payment failed");
        }
        // Generate booking reference
        String bookingRef = bookingReferenceGenerator.generate();
        // Create booking entity
        Booking booking = new Booking();
        booking.setBookingRef(bookingRef);
        booking.setFlightId(request.getFlightId());
        booking.setPassengerInfo(request.getPassengerInfo());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalPrice(payment.getAmount());
        booking.setPayment(payment);
        // Save booking
        bookingRepository.save(booking);
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setBookingRef(bookingRef);
        response.setStatus("CONFIRMED");
        response.setTicket(booking);
        return response;
    }
}
