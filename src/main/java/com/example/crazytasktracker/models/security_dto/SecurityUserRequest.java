package com.example.crazytasktracker.models.security_dto;

import com.example.crazytasktracker.models.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityUserRequest {
    private String username;
    private String phoneNumber;
    private String password;
    private Set<RoleType> roles;

}
