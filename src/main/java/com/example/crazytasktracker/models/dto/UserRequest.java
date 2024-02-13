package com.example.crazytasktracker.models.dto;

import com.example.crazytasktracker.models.entities.RoleType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserRequest {
    private String username;
    private String phoneNumber;
    private String password;
    private Set<RoleType> roles;

}
