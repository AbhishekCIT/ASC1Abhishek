package com.example.airbooking.repository;

import com.example.airbooking.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Passenger entity.
 */
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
}
