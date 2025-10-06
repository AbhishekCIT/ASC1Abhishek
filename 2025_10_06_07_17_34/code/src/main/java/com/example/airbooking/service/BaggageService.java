package com.example.airbooking.service;

import com.example.airbooking.model.Baggage;
import com.example.airbooking.repository.BaggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service for baggage management (info, update, tracking).
 */
@Service
public class BaggageService {
    @Autowired
    private BaggageRepository baggageRepository;

    /**
     * View baggage info for a booking.
     * @param bookingId Booking ID
     * @return List of Baggage
     */
    public List<Baggage> viewBaggageInfo(String bookingId) {
        return baggageRepository.findByBookingId(bookingId);
    }

    /**
     * Add or update baggage for a booking.
     * @param bookingId Booking ID
     * @param bags List of Baggage
     * @return List of updated Baggage
     */
    @Transactional
    public List<Baggage> updateBaggage(String bookingId, List<Baggage> bags) {
        // Example validation: max 2 bags, max 32kg per bag
        if (bags.size() > 2) {
            throw new IllegalArgumentException("Baggage limit exceeded.");
        }
        for (Baggage b : bags) {
            if (b.getWeight() > 32) {
                throw new IllegalArgumentException("Baggage weight limit exceeded.");
            }
            if (b.getBaggageId() == null) {
                b.setBaggageId(UUID.randomUUID().toString());
            }
            b.setBookingId(bookingId);
            baggageRepository.save(b);
        }
        return baggageRepository.findByBookingId(bookingId);
    }

    /**
     * Track baggage by baggage ID.
     * @param baggageId Baggage ID
     * @return Baggage
     */
    public Baggage trackBaggage(String baggageId) {
        return baggageRepository.findById(baggageId).orElseThrow(() -> new RuntimeException("Baggage not found."));
    }
}