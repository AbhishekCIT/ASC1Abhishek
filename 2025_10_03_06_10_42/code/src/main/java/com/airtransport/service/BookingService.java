package com.airtransport.service;

import com.airtransport.dto.BookingRequest;
import com.airtransport.dto.BookingResponse;
import com.airtransport.dto.PassengerDTO;
import com.airtransport.exception.InvalidPassengerException;
import com.airtransport.exception.PaymentFailedException;
import com.airtransport.exception.SeatUnavailableException;
import com.airtransport.model.Booking;
import com.airtransport.model.Passenger;
import com.airtransport.model.Seat;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service to handle booking creation and logic.
 */
@Service
public class BookingService {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private SeatLockService seatLockService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SeatRepository seatRepository;

    /**
     * Creates a booking, locks seats, processes payment, and sends confirmation.
     * @param request BookingRequest
     * @return BookingResponse
     */
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        // Validate passenger details
        List<PassengerDTO> passengers = request.getPassengerDetails();
        if (passengers == null || passengers.isEmpty()) {
            throw new InvalidPassengerException("Passenger details are required.");
        }
        // Lock seats
        List<String> seatNumbers = request.getSeatSelection();
        if (seatNumbers != null && !seatNumbers.isEmpty()) {
            for (String seat : seatNumbers) {
                if (!seatLockService.lockSeat(request.getFlightId(), seat)) {
                    throw new SeatUnavailableException("Selected seat is unavailable.");
                }
            }
        }
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(request.getPaymentMethod(), request.getPaymentInfo());
        if (!paymentSuccess) {
            throw new PaymentFailedException("Payment failed. Please retry.");
        }
        // Create booking record
        Booking booking = new Booking();
        booking.setFlightId(request.getFlightId());
        booking.setBookingReference(UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);
        // Save passengers
        List<Passenger> passengerEntities = passengers.stream().map(dto -> {
            Passenger p = new Passenger();
            p.setBooking(savedBooking);
            p.setName(dto.getName());
            p.setDob(dto.getDob());
            p.setEmail(dto.getEmail());
            return p;
        }).collect(Collectors.toList());
        savedBooking.setPassengers(passengerEntities);
        // Save seats as booked
        if (seatNumbers != null) {
            for (String seat : seatNumbers) {
                Seat seatEntity = seatRepository.findByFlightIdAndSeatNumber(request.getFlightId(), seat);
                if (seatEntity != null) {
                    seatEntity.setStatus("BOOKED");
                    seatRepository.save(seatEntity);
                }
            }
        }
        // Send confirmation email
        emailService.sendConfirmation(passengers.get(0).getEmail(), savedBooking);
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setBookingReference(savedBooking.getBookingReference());
        response.setStatus(savedBooking.getStatus());
        response.setReceipt(Map.of("amount", request.getPaymentInfo().get("amount"), "method", request.getPaymentMethod()));
        response.setItinerary(Map.of("flightId", request.getFlightId()));
        return response;
    }
}
