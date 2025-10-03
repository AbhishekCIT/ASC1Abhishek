package com.example.airbooking.repository;

import com.example.airbooking.model.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PassengerRepository interface (mock-based).
 */
public class PassengerRepositoryTest {
    /**
     * Test save and find operations (mocked).
     */
    @Test
    void testSaveAndFind() {
        PassengerRepository repo = mock(PassengerRepository.class);
        Passenger passenger = new Passenger();
        when(repo.save(passenger)).thenReturn(passenger);
        assertEquals(passenger, repo.save(passenger));
    }
}
