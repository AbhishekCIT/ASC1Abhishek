package com.example.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Utility for generating unique booking references.
 */
@Component
public class BookingReferenceGenerator {
    /**
     * Generate a unique booking reference.
     * @return Booking reference string
     */
    public String generateReference() {
        return "BR" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
