package com.asc.airbooking.repository;

import com.asc.airbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Booking entity data access.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    /**
     * Finds booking by confirmation ID.
     * @param confirmationId Confirmation ID
     * @return Optional Booking
     */
    Optional<Booking> findByConfirmationId(String confirmationId);
}
