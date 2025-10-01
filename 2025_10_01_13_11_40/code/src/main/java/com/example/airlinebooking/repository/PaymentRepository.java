package com.example.airlinebooking.repository;

import com.example.airlinebooking.entity.Payment;
import com.example.airlinebooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByBooking(Booking booking);
}
