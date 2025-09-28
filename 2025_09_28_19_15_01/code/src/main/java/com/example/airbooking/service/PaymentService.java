package com.example.airbooking.service;

import com.example.airbooking.model.PaymentRequest;
import com.example.airbooking.model.PaymentResponse;
import com.example.airbooking.entity.Payment;
import com.example.airbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service for handling payment processing with payment gateways.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Process payment for a booking.
     * @param request PaymentRequest
     * @return PaymentResponse
     */
    public PaymentResponse processPayment(PaymentRequest request) {
        // TODO: Integrate with payment gateway (Stripe/PayPal)
        // For demonstration, assume payment is always successful
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getPaymentId());
        response.setStatus("SUCCESS");
        return response;
    }
}
