package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AirlineEntity (POJO).
 */
class AirlineEntityTest {
    private AirlineEntity airlineEntity;

    @BeforeEach
    void setUp() {
        airlineEntity = new AirlineEntity();
    }

    /**
     * Test setting and getting airlineId.
     */
    @Test
    @DisplayName("Set and get airlineId")
    void testAirlineId() {
        airlineEntity.setAirlineId(100L);
        assertEquals(100L, airlineEntity.getAirlineId());
    }

    /**
     * Test setting and getting name.
     */
    @Test
    @DisplayName("Set and get name")
    void testName() {
        airlineEntity.setName("Delta");
        assertEquals("Delta", airlineEntity.getName());
    }

    /**
     * Test setting and getting code.
     */
    @Test
    @DisplayName("Set and get code")
    void testCode() {
        airlineEntity.setCode("DL");
        assertEquals("DL", airlineEntity.getCode());
    }

    /**
     * Test default values (should be null).
     */
    @Test
    @DisplayName("Default values are null")
    void testDefaultValues() {
        assertNull(airlineEntity.getAirlineId());
        assertNull(airlineEntity.getName());
        assertNull(airlineEntity.getCode());
    }
}
