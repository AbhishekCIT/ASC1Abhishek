package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Payment entity.
 * Purpose: Verify builder, getters/setters, equals/hashCode, and edge/boundary cases.
 */
public class PaymentTest {
    private Payment payment;
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = Booking.builder().id(1).build();
        payment = Payment.builder()
                .id(1)
                .booking(booking)
                .amount(BigDecimal.valueOf(100.00))
                .status("SUCCESS")
                .paidAt(LocalDateTime.now())
                .build();
    }

    /**
     * Test: Builder and getters
     */
    @Test
    void testBuilderAndGetters() {
        assertEquals(1, payment.getId());
        assertEquals(booking, payment.getBooking());
        assertEquals(BigDecimal.valueOf(100.00), payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
        assertNotNull(payment.getPaidAt());
    }

    /**
     * Test: Setters
     */
    @Test
    void testSetters() {
        payment.setStatus("FAILED");
        assertEquals("FAILED", payment.getStatus());
    }

    /**
     * Test: Equals and hashCode
     */
    @Test
    void testEqualsAndHashCode() {
        Payment payment2 = Payment.builder()
                .id(1)
                .booking(booking)
                .amount(BigDecimal.valueOf(100.00))
                .status("SUCCESS")
                .paidAt(payment.getPaidAt())
                .build();
        assertEquals(payment, payment2);
        assertEquals(payment.hashCode(), payment2.hashCode());
    }

    /**
     * Test: Edge case - null fields
     */
    @Test
    void testNullFields() {
        Payment nullPayment = new Payment();
        assertNull(nullPayment.getBooking());
        assertNull(nullPayment.getAmount());
        assertNull(nullPayment.getStatus());
        assertNull(nullPayment.getPaidAt());
    }

    /**
     * Test: Boundary case - zero amount
     */
    @Test
    void testZeroAmount() {
        payment.setAmount(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, payment.getAmount());
    }

    /**
     * Test: Boundary case - negative amount
     */
    @Test
    void testNegativeAmount() {
        payment.setAmount(BigDecimal.valueOf(-50));
        assertEquals(BigDecimal.valueOf(-50), payment.getAmount());
    }
}
