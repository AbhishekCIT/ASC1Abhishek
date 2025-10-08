package com.example.airbooking.service;

import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.PaymentRepository;
import com.example.airbooking.exception.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for integrating with payment gateway (Stripe/PayPal).
 */
@Service
public class PaymentGatewayService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Process payment for a booking.
     * @param request the payment request
     * @return payment response
     */
    public PaymentResponse processPayment(PaymentRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new PaymentException("Booking not found"));
        // TODO: Integrate with Stripe/PayPal API
        // Mock payment processing
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(request.getPaymentInfo().getAmount());
        payment.setStatus("SUCCESS");
        payment.setTransactionId("txn" + System.currentTimeMillis());
        payment.setProcessedAt(LocalDateTime.now());
        payment = paymentRepository.save(payment);
        // Update booking status
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        return new PaymentResponse(payment.getStatus(), payment.getTransactionId());
    }
}
