package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Payment;
import com.example.airbooking.model.PaymentInfo;
import com.example.airbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service for processing payments via payment gateway.
 */
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Processes payment for a booking.
     * @param paymentInfo payment details
     * @param booking booking entity
     * @param amount amount to charge
     * @return payment entity
     */
    public Payment processPayment(PaymentInfo paymentInfo, Booking booking, Double amount) {
        // Simulate payment gateway integration (replace with real API call)
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentInfo.getPaymentMethod());
        payment.setTransactionId("TXN" + System.currentTimeMillis());
        payment.setCreatedAt(LocalDateTime.now());
        if (paymentInfo.isValid()) {
            payment.setStatus("CONFIRMED");
        } else {
            payment.setStatus("FAILED");
        }
        return paymentRepository.save(payment);
    }
}
