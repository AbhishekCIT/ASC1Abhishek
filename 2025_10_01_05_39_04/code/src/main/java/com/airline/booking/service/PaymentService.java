package com.airline.booking.service;

import com.airline.booking.model.Booking;
import com.airline.booking.model.Payment;
import com.airline.booking.repository.BookingRepository;
import com.airline.booking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service for payment processing and integration with payment gateway.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    /**
     * Process payment for a booking.
     * @param bookingId booking ID
     * @param amount amount
     * @param method payment method
     * @return Payment
     */
    @Transactional
    public Payment processPayment(String bookingId, BigDecimal amount, String method) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Payment details are required");
        }
        // Simulate payment gateway integration
        boolean paymentSuccess = true; // Replace with real gateway call
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setPaymentTime(LocalDateTime.now());
        if (paymentSuccess) {
            payment.setStatus("SUCCESS");
        } else {
            payment.setStatus("FAILED");
            throw new RuntimeException("Payment authorization failed");
        }
        return paymentRepository.save(payment);
    }
}
