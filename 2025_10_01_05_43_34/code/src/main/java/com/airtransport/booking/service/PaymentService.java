package com.airtransport.booking.service;

import com.airtransport.booking.entity.Booking;
import com.airtransport.booking.entity.Payment;
import com.airtransport.booking.model.BookingRequest;
import com.airtransport.booking.model.PaymentRequest;
import com.airtransport.booking.repository.BookingRepository;
import com.airtransport.booking.repository.PaymentRepository;
import com.airtransport.booking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final ValidationUtil validationUtil;

    /**
     * Processes payment for a booking. Throws exception if validation fails.
     */
    @Transactional
    public Payment processPayment(PaymentRequest request) {
        Optional<Booking> bookingOpt = bookingRepository.findById(request.getBookingRef());
        if (bookingOpt.isEmpty()) {
            throw new IllegalArgumentException("Booking reference invalid");
        }
        Booking booking = bookingOpt.get();
        BookingRequest.PaymentInfo paymentInfo = request.getPaymentInfo();
        if (!validationUtil.isValidCardNumber(paymentInfo.getCardNumber()) ||
            !validationUtil.isValidCVV(paymentInfo.getCvv())) {
            throw new IllegalArgumentException("Invalid payment details");
        }
        // Simulate payment gateway integration
        String status = "SUCCESS";
        String transactionId = UUID.randomUUID().toString();
        Payment payment = Payment.builder()
                .id(transactionId)
                .booking(booking)
                .amount(booking.getTotalPrice())
                .status(status)
                .paymentDate(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);
        // Update booking status
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        log.info("Payment processed for bookingRef {}: status {}", booking.getId(), status);
        return payment;
    }
}
