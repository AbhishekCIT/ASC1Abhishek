package com.example.airtransport.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingReferenceGenerator.
 */
class BookingReferenceGeneratorTest {

    private final BookingReferenceGenerator generator = new BookingReferenceGenerator();

    @Test
    @DisplayName("Test generate returns unique booking reference with correct format")
    void testGenerate_UniqueAndFormat() {
        String ref1 = generator.generate();
        String ref2 = generator.generate();
        assertNotNull(ref1);
        assertNotNull(ref2);
        assertTrue(ref1.startsWith("BR"));
        assertTrue(ref2.startsWith("BR"));
        assertEquals(10, ref1.length()); // 'BR' + 8 chars
        assertEquals(10, ref2.length());
        assertNotEquals(ref1, ref2);
        assertTrue(ref1.matches("BR[A-Z0-9]{8}"));
    }

    @Test
    @DisplayName("Test generate does not return null or empty string")
    void testGenerate_NotNullOrEmpty() {
        String ref = generator.generate();
        assertNotNull(ref);
        assertFalse(ref.isEmpty());
    }
}
