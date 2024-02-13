package com.example.crazytasktracker.models.dto;

import com.example.crazytasktracker.models.entities.RoleType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String phoneNumber;
    private String username;
    private Set<RoleType> roles;
    private List<TaskResponse> tasks = new ArrayList<>();

}
