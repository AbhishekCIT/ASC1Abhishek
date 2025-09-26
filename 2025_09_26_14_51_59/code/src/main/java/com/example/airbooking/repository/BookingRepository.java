package com.example.airbooking.repository;

import com.example.airbooking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for booking data access.
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    // Custom query methods can be added here
}
