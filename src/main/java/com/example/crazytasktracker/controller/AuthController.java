package com.example.crazytasktracker.controller;

import com.example.crazytasktracker.exception.AlreadyExistsException;
import com.example.crazytasktracker.models.dto.UserResponse;
import com.example.crazytasktracker.models.security_dto.*;
import com.example.crazytasktracker.repository.UserRepository;
import com.example.crazytasktracker.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(securityService.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> registerUser(@RequestBody SecurityUserRequest request){
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new AlreadyExistsException("User with this phoneNumber already exists!");
        }
        if (userRepository.existsByUsername(request.getUsername())){
            throw new AlreadyExistsException("User with this username already exists!");
        }
        securityService.register(request);
        return ResponseEntity.ok(new SimpleResponse("Account created!"));
    }
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(securityService.response(request));
    }
    @PostMapping("/logout")
    public ResponseEntity<SimpleResponse> logoutUser(@AuthenticationPrincipal UserDetails userDetails){
        securityService.logout();
        return ResponseEntity.ok(new SimpleResponse("User logout. Username is: "+userDetails.getUsername()));
    }















}
