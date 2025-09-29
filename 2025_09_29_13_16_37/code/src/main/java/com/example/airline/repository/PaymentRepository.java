package com.example.airline.repository;

import com.example.airline.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByBooking_BookingId(String bookingId);
}