package com.example.airtransport.repository;

import com.example.airtransport.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Booking entity.
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, String> {
    // Additional query methods can be defined here
}
