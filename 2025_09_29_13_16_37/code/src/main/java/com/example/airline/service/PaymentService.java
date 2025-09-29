package com.example.airline.service;

import com.example.airline.dto.BookingRequest;
import com.example.airline.dto.PaymentRequest;
import com.example.airline.dto.PaymentResponse;
import com.example.airline.model.Booking;
import com.example.airline.model.Payment;
import com.example.airline.repository.BookingRepository;
import com.example.airline.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Processes payment for a booking.
     * @param request PaymentRequest or BookingRequest
     * @return PaymentResponse
     */
    public PaymentResponse processPayment(Object request) {
        String bookingId;
        BigDecimal amount;
        if (request instanceof PaymentRequest) {
            bookingId = ((PaymentRequest) request).getBookingId();
            amount = ((PaymentRequest) request).getAmount();
        } else if (request instanceof BookingRequest) {
            bookingId = ((BookingRequest) request).getFlightId(); // This should be bookingId, but BookingRequest doesn't have it yet
            amount = null; // Should be set from flight price
        } else {
            throw new IllegalArgumentException("Invalid payment request");
        }
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (!bookingOpt.isPresent()) {
            throw new RuntimeException("Booking not found");
        }
        // Simulate payment gateway integration
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setBooking(bookingOpt.get());
        payment.setAmount(amount != null ? amount : new BigDecimal("0"));
        payment.setStatus("SUCCESS");
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus("SUCCESS");
        response.setTransactionId(payment.getPaymentId());
        return response;
    }
}