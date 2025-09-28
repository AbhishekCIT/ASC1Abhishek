package com.example.airlinebooking.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Utility class for generating unique booking references.
 */
@Component
public class BookingReferenceUtil {
    /**
     * Generates a unique booking reference string.
     * @return unique booking reference
     */
    public String generateBookingReference() {
        // Example: Use first 6 chars of UUID (uppercase)
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }
}
