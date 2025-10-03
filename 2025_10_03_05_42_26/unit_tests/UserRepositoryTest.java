package com.airtransport.repository;

import com.airtransport.entity.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for UserRepository.
 * Covers CRUD operations, edge cases, and error scenarios.
 */
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * Test saving and retrieving a user (normal case).
     */
    @Test
    void testSaveAndFindById_Normal() {
        UserEntity user = new UserEntity();
        user.setUserId("U123");
        user.setName("John Doe");
        user.setEmail("john@example.com");
        userRepository.save(user);
        Optional<UserEntity> found = userRepository.findById("U123");
        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
    }

    /**
     * Test finding a non-existent user (edge case).
     */
    @Test
    void testFindById_NotFound() {
        Optional<UserEntity> found = userRepository.findById("NON_EXISTENT");
        assertFalse(found.isPresent());
    }

    /**
     * Test deleting a user (normal case).
     */
    @Test
    void testDeleteById_Normal() {
        UserEntity user = new UserEntity();
        user.setUserId("U456");
        userRepository.save(user);
        userRepository.deleteById("U456");
        Optional<UserEntity> found = userRepository.findById("U456");
        assertFalse(found.isPresent());
    }

    /**
     * Test saving user with null ID (error scenario).
     */
    @Test
    void testSave_NullId() {
        UserEntity user = new UserEntity();
        user.setUserId(null);
        assertThrows(Exception.class, () -> userRepository.save(user));
    }
}
