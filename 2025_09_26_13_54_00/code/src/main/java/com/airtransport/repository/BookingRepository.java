package com.airtransport.repository;

import com.airtransport.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookingRepository for CRUD operations on Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Additional query methods can be defined here
}
