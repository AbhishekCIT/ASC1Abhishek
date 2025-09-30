package com.example.airlinebooking.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Utility for generating unique booking references.
 */
@Component
public class BookingReferenceGenerator {
    /**
     * Generates a unique booking reference.
     * @return booking reference string
     */
    public String generateRef() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
