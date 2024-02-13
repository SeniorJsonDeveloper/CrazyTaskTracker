package com.example.crazytasktracker.models.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskResponseList {
    private List<TaskResponse> tasks = new ArrayList<>();
}
