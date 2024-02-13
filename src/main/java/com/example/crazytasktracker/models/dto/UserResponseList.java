package com.example.crazytasktracker.models.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseList {
    private List<UserResponse> userTasks = new ArrayList<>();
}
