package com.example.flightbooking.service;

import com.example.flightbooking.model.Booking;
import com.example.flightbooking.model.FareRules;
import org.springframework.stereotype.Service;

/**
 * Service for applying airline fare rules for eligibility.
 */
@Service
public class AirlineFareRulesService {
    public FareRules getFareRules(String flightId) {
        // Integrate with airline system for fare rules (stub)
        FareRules rules = new FareRules();
        rules.setModificationFee(50.0);
        rules.setCancellationFee(25.0);
        return rules;
    }
    public boolean isEligibleForModification(Booking booking, FareRules rules) {
        // Implement eligibility logic (stub)
        return "CONFIRMED".equals(booking.getStatus());
    }
    public boolean isEligibleForCancellation(Booking booking, FareRules rules) {
        // Implement eligibility logic (stub)
        return "CONFIRMED".equals(booking.getStatus());
    }
}
