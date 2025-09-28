package com.example.airlinebooking.service;

import com.example.airlinebooking.entity.Booking;
import com.example.airlinebooking.entity.Payment;
import com.example.airlinebooking.exception.PaymentFailedException;
import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.model.PaymentCallbackRequest;
import com.example.airlinebooking.model.PaymentCallbackResponse;
import com.example.airlinebooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service for payment processing logic.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Processes payment for a booking.
     * @param booking booking entity
     * @param paymentDetails payment details from request
     */
    @Transactional
    public void processPayment(Booking booking, BookingRequest.PaymentDetails paymentDetails) {
        // Calculate total amount (for simplicity, use flight price * passengers)
        BigDecimal amount = booking.getFlight().getPrice().multiply(
                BigDecimal.valueOf(booking.getPassengers().size()));
        // Call payment gateway
        boolean paymentSuccess = paymentGatewayClient.initiatePayment(paymentDetails, amount);
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentTime(LocalDateTime.now());
        if (paymentSuccess) {
            payment.setStatus("SUCCESS");
        } else {
            payment.setStatus("FAILED");
            throw new PaymentFailedException("Payment failed");
        }
        paymentRepository.save(payment);
        booking.setPayment(payment);
    }

    /**
     * Handles payment callback from gateway.
     * @param request callback request
     * @return callback response
     */
    public PaymentCallbackResponse handlePaymentCallback(PaymentCallbackRequest request) {
        // For demo: just return the status
        PaymentCallbackResponse response = new PaymentCallbackResponse();
        response.setPaymentStatus(request.getStatus());
        return response;
    }
}
