package com.airtransport.repository;

import com.airtransport.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    // Custom query methods can be added here if needed
}
