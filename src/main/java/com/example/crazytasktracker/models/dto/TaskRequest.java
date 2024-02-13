package com.example.crazytasktracker.models.dto;

import lombok.Data;

@Data
public class TaskRequest {
    private String taskname;
    private String description;
    private String requireDate;

}
