package com.example.airbooking.repository;

import com.example.airbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByConfirmationNumber(String confirmationNumber);
}
