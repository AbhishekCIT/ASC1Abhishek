package com.example.scheduler.entity;

import javax.persistence.*;

/**
 * Entity representing a recipient for a scheduled report.
 */
@Entity
@Table(name = "recipient", uniqueConstraints = {@UniqueConstraint(columnNames = {"schedule_id", "email"})})
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
