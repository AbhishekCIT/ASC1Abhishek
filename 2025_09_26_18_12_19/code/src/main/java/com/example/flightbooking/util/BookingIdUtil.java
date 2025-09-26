package com.example.flightbooking.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Utility for generating unique booking IDs.
 */
@Component
public class BookingIdUtil {
    public static String generateId() {
        return "B" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
