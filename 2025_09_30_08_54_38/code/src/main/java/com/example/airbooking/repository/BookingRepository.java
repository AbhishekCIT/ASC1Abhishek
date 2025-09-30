package com.example.airbooking.repository;

import com.example.airbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByConfirmationNumber(String confirmationNumber);
    List<Booking> findByUserId(Long userId);
}
