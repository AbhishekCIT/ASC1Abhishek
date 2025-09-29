package com.example.airline.repository;

import com.example.airline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // Additional query methods if needed
}