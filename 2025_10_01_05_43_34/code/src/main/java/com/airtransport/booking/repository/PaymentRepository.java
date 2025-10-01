package com.airtransport.booking.repository;

import com.airtransport.booking.entity.Payment;
import com.airtransport.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Optional<Payment> findByBooking(Booking booking);
}
