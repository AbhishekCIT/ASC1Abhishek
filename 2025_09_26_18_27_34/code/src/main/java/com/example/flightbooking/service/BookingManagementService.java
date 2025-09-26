package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.BookingRepository;
import com.example.flightbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing bookings: view, modify, cancel.
 */
@Service
public class BookingManagementService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AirlineFareRulesService airlineFareRulesService;
    @Autowired
    private RefundProcessingService refundProcessingService;
    @Autowired
    private UserRepository userRepository;

    public List<Booking> viewBookings(Long userId, String token) {
        User user = userService.authenticate(token);
        if (!user.getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }
        return bookingRepository.findAll().stream()
                .filter(b -> b.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingModificationResult modifyBooking(String bookingReference, BookingModificationRequest request, String token) {
        Booking booking = bookingRepository.findByBookingReference(bookingReference);
        if (booking == null) throw new RuntimeException("Booking not found");
        User user = userService.authenticate(token);
        if (!booking.getUser().getId().equals(user.getId())) throw new RuntimeException("Unauthorized access");
        FareRules fareRules = airlineFareRulesService.getFareRules(booking.getFlight().getFlightId());
        if (!airlineFareRulesService.isEligibleForModification(booking, fareRules)) {
            throw new RuntimeException("Not eligible for modification");
        }
        // Apply modification (e.g., change date, passenger details)
        booking.setStatus("MODIFIED");
        booking.setPassengerDetails(request.getPassengerDetails());
        bookingRepository.save(booking);
        // Calculate fees/refund
        double fees = fareRules.getModificationFee();
        double refund = 0.0;
        return new BookingModificationResult(booking.getBookingReference(), booking.getStatus(), fees, refund);
    }

    @Transactional
    public BookingCancellationResult cancelBooking(String bookingReference, String token) {
        Booking booking = bookingRepository.findByBookingReference(bookingReference);
        if (booking == null) throw new RuntimeException("Booking not found");
        User user = userService.authenticate(token);
        if (!booking.getUser().getId().equals(user.getId())) throw new RuntimeException("Unauthorized access");
        FareRules fareRules = airlineFareRulesService.getFareRules(booking.getFlight().getFlightId());
        if (!airlineFareRulesService.isEligibleForCancellation(booking, fareRules)) {
            throw new RuntimeException("Not eligible for cancellation");
        }
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
        // Process refund
        RefundResult refundResult = refundProcessingService.processRefund(booking);
        double refund = refundResult.getAmount();
        double fees = fareRules.getCancellationFee();
        return new BookingCancellationResult(booking.getBookingReference(), booking.getStatus(), refund, fees);
    }
}
