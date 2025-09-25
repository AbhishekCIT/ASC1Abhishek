package com.example.scheduling.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportType entity.
 */
public class ReportTypeTest {

    /**
     * Test all getters and setters for normal scenario.
     */
    @Test
    void testGettersAndSetters_Normal() {
        ReportType reportType = new ReportType();
        reportType.setId(1L);
        reportType.setName("Sales");
        assertEquals(1L, reportType.getId());
        assertEquals("Sales", reportType.getName());
    }

    /**
     * Test setting and getting null values (edge case).
     */
    @Test
    void testSettersAndGetters_NullValues() {
        ReportType reportType = new ReportType();
        reportType.setId(null);
        reportType.setName(null);
        assertNull(reportType.getId());
        assertNull(reportType.getName());
    }

    /**
     * Test equals and hashCode for same id.
     */
    @Test
    void testEqualsAndHashCode_SameId() {
        ReportType r1 = new ReportType();
        r1.setId(1L);
        r1.setName("Sales");
        ReportType r2 = new ReportType();
        r2.setId(1L);
        r2.setName("Marketing");
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    /**
     * Test equals for different id.
     */
    @Test
    void testEquals_DifferentId() {
        ReportType r1 = new ReportType();
        r1.setId(1L);
        ReportType r2 = new ReportType();
        r2.setId(2L);
        assertNotEquals(r1, r2);
    }

    /**
     * Test equals for null and different class (edge case).
     */
    @Test
    void testEquals_NullAndDifferentClass() {
        ReportType r1 = new ReportType();
        r1.setId(1L);
        assertNotEquals(r1, null);
        assertNotEquals(r1, "string");
    }
}
