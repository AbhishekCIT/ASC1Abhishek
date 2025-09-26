package com.example.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Utility for generating unique booking references.
 */
@Component
public class BookingReferenceGenerator {
    public String generate() {
        // Generate a unique booking reference
        return "BR" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
