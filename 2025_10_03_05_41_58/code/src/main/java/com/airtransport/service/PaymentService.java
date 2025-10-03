package com.airtransport.service;

import com.airtransport.entity.Booking;
import com.airtransport.entity.Payment;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Service for processing payments.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    /**
     * Process payment for a booking.
     * @param bookingId Booking identifier
     * @param amount Amount to be paid
     * @param paymentMethod Payment method (e.g., stripe, paypal)
     * @return Payment result (mocked)
     */
    @Transactional
    public String processPayment(String bookingId, BigDecimal amount, String paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking does not exist"));
        // TODO: Integrate with payment gateway (Stripe, PayPal)
        // For now, assume payment is always successful
        Payment payment = Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .booking(booking)
                .amount(amount)
                .method(paymentMethod)
                .status("SUCCESS")
                .build();
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }
}
