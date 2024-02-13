package com.example.crazytasktracker.models.security_dto;

import com.example.crazytasktracker.models.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String token;
    private String username;
    private String phoneNumber;
    private String refreshToken;
    private List<String> roles;
}
