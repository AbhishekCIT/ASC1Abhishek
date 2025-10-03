package com.example.airbooking.service;

import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.exception.BookingNotFoundException;
import com.example.airbooking.exception.PaymentFailedException;
import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.PaymentRepository;
import com.example.airbooking.util.PaymentGatewayClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service for processing payments.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentGatewayClientUtil paymentGatewayClientUtil;

    /**
     * Process payment for a booking.
     */
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        // Simulate payment processing
        boolean paid = paymentGatewayClientUtil.pay(
                booking.getFlight().getPrice(),
                request.getPaymentMethod(),
                request.getCardDetails().getCardNumber(),
                request.getCardDetails().getCardHolder(),
                request.getCardDetails().getExpiry(),
                request.getCardDetails().getCvv()
        );
        if (!paid) {
            throw new PaymentFailedException("Payment failed");
        }
        Payment payment = Payment.builder()
                .booking(booking)
                .amount(booking.getFlight().getPrice())
                .status("SUCCESS")
                .paidAt(LocalDateTime.now())
                .build();
        payment = paymentRepository.save(payment);
        // Update booking status
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .timestamp(payment.getPaidAt().toString())
                .build();
    }
}
