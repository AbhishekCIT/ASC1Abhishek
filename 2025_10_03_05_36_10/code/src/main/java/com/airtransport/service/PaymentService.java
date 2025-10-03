package com.airtransport.service;

import com.airtransport.model.Booking;
import com.airtransport.model.Payment;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.PaymentRepository;
import com.airtransport.util.PaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Process payment for a booking
     * @param bookingId Booking ID
     * @param paymentDetails Payment details (card info, etc.)
     * @return Payment object
     */
    @Transactional
    public Payment processPayment(String bookingId, Map<String, String> paymentDetails) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        // Validate payment details (simplified)
        if (!paymentDetails.containsKey("cardNumber") || paymentDetails.get("cardNumber").length() < 4) {
            throw new IllegalArgumentException("Invalid card details");
        }
        // Call external payment gateway
        boolean paymentSuccess = paymentGatewayClient.callPaymentGateway(paymentDetails, booking.getTotalAmount());
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(paymentSuccess ? "SUCCESS" : "FAILED");
        paymentRepository.save(payment);
        booking.setPayment(payment);
        bookingRepository.save(booking);
        if (!paymentSuccess) {
            throw new IllegalStateException("Payment failed");
        }
        return payment;
    }
}
