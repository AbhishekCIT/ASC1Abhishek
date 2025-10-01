package com.airline.booking.repository;

import com.airline.booking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Passenger entity.
 */
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
}
