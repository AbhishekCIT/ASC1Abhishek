package com.airtransport.util;

import org.springframework.stereotype.Component;

/**
 * Utility for calculating final price including taxes and fees.
 */
@Component
public class PricingUtil {
    /**
     * Calculate the final price for a flight (mock logic).
     * @param flightId Flight ID
     * @return Final price
     */
    public double calculatePrice(String flightId) {
        // TODO: Integrate with pricing engine or database
        // For demo, return a fixed price + taxes/fees
        double basePrice = 350.00;
        double taxes = basePrice * 0.10; // 10% tax
        double fees = 25.00; // fixed fees
        return basePrice + taxes + fees;
    }
}
