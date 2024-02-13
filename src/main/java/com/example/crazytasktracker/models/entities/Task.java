package com.example.crazytasktracker.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String taskName;
    private String description;
    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private String requireDate;
    @ManyToOne
    private User user;
}
