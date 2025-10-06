package com.example.airbooking.repository;

import com.example.airbooking.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, String> {
    List<Baggage> findByBookingId(String bookingId);
}