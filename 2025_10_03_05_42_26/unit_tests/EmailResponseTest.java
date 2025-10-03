package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for EmailResponse.
 * Covers constructors, getters/setters, normal, edge, and boundary cases.
 */
class EmailResponseTest {
    private EmailResponse emailResponse;

    @BeforeEach
    void setUp() {
        emailResponse = new EmailResponse();
    }

    /**
     * Test default constructor.
     */
    @Test
    void testDefaultConstructor() {
        assertNull(emailResponse.getNotificationStatus());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        EmailResponse response = new EmailResponse("SENT");
        assertEquals("SENT", response.getNotificationStatus());
    }

    /**
     * Test setting and getting notificationStatus (normal case).
     */
    @Test
    void testNotificationStatus_Normal() {
        emailResponse.setNotificationStatus("FAILED");
        assertEquals("FAILED", emailResponse.getNotificationStatus());
    }

    /**
     * Test edge case: setting null value.
     */
    @Test
    void testSetters_NullValue() {
        emailResponse.setNotificationStatus(null);
        assertNull(emailResponse.getNotificationStatus());
    }

    /**
     * Test boundary case: empty string.
     */
    @Test
    void testSetters_EmptyString() {
        emailResponse.setNotificationStatus("");
        assertEquals("", emailResponse.getNotificationStatus());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
