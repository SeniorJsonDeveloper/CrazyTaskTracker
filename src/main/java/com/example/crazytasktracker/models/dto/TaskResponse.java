package com.example.crazytasktracker.models.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TaskResponse {
    private Long id;
    private String taskname;
    private String description;
    private String requireDate;
    private Instant createdAt = Instant.now();
}
