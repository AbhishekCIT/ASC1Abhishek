package com.airtransport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity representing a user.
 */
@Entity
@Data
public class User {
    @Id
    private String userId;
    private String email;
    private String name;
}
