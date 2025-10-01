package com.example.airlinebooking.service;

import com.example.airlinebooking.entity.Booking;
import com.example.airlinebooking.entity.Payment;
import com.example.airlinebooking.model.PaymentRequest;
import com.example.airlinebooking.model.PaymentResponse;
import com.example.airlinebooking.repository.BookingRepository;
import com.example.airlinebooking.repository.PaymentRepository;
import com.example.airlinebooking.util.PaymentGatewayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for handling payment logic and integration.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentGatewayClient paymentGatewayClient;

    /**
     * Process payment for a booking.
     */
    @Transactional
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        // Validate payment details (PCI DSS compliance assumed)
        boolean paymentSuccess = paymentGatewayClient.executePayment(paymentRequest.getPaymentDetails());
        String paymentId = UUID.randomUUID().toString();
        Payment payment = Payment.builder()
                .paymentId(paymentId)
                .booking(booking)
                .amount(booking.getFlight().getFare())
                .status(paymentSuccess ? "SUCCESS" : "FAILED")
                .paymentDate(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);
        return PaymentResponse.builder()
                .paymentId(paymentId)
                .status(paymentSuccess ? "SUCCESS" : "FAILED")
                .build();
    }
}
