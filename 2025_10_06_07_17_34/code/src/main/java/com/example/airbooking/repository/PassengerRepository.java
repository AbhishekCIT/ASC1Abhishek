package com.example.airbooking.repository;

import com.example.airbooking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
    List<Passenger> findByBookingId(String bookingId);
}