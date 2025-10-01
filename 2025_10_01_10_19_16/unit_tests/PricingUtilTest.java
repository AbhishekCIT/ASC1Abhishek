package com.airtransport.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PricingUtil.
 */
class PricingUtilTest {

    private PricingUtil pricingUtil;

    @BeforeEach
    void setUp() {
        pricingUtil = new PricingUtil();
    }

    /**
     * Test price calculation for a flight (normal scenario).
     */
    @Test
    void testCalculatePrice_Normal() {
        double price = pricingUtil.calculatePrice("F123");
        // 350 + 10% tax + 25 fee = 350 + 35 + 25 = 410
        assertEquals(410.0, price, 0.001);
    }

    /**
     * Test price calculation with null flightId (edge case).
     */
    @Test
    void testCalculatePrice_NullFlightId() {
        double price = pricingUtil.calculatePrice(null);
        assertEquals(410.0, price, 0.001);
    }
}
