package com.airline.booking.repository;

import com.airline.booking.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByBookingId(String bookingId);
}
