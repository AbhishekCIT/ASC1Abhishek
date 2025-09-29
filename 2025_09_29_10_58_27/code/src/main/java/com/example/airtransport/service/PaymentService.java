package com.example.airtransport.service;

import com.example.airtransport.model.PaymentRequest;
import com.example.airtransport.model.PaymentResponse;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.repository.PaymentRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service to handle payment processing and validation.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    /**
     * Process payment for a booking. Stub for payment gateway integration.
     */
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // Validate booking
        var booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ValidationException("Invalid booking ID"));
        // Validate payment info (stub)
        if (request.getPaymentInfo() == null || request.getPaymentInfo().isEmpty()) {
            throw new ValidationException("Invalid payment information");
        }
        // TODO: Integrate with payment gateway
        // Save payment record
        var payment = new com.example.airtransport.model.Payment();
        payment.setBooking(booking);
        payment.setStatus("SUCCESS");
        payment.setPaymentId(UUID.randomUUID().toString());
        paymentRepository.save(payment);
        // Build response
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getPaymentId());
        response.setStatus(payment.getStatus());
        return response;
    }
}
