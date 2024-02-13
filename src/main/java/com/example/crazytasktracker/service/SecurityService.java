package com.example.crazytasktracker.service;

import com.example.crazytasktracker.exception.RefreshTokenException;
import com.example.crazytasktracker.models.entities.RefreshToken;
import com.example.crazytasktracker.models.entities.User;
import com.example.crazytasktracker.models.mappers.UserMapper;
import com.example.crazytasktracker.models.security_dto.*;
import com.example.crazytasktracker.repository.UserRepository;
import com.example.crazytasktracker.security.ApplicationUserDetails;
import com.example.crazytasktracker.security.RefreshTokenService;
import com.example.crazytasktracker.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;


    public AuthResponse authenticate(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ApplicationUserDetails userDetails = (ApplicationUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return AuthResponse
                .builder()
                .id(userDetails.getId())
                .token(jwtUtils.generateJwtToken(userDetails))
                .refreshToken(refreshToken.getToken())
                .username(userDetails.getUsername())
                .phoneNumber(userDetails.getPhoneNumber())
                .roles(roles)
                .build();
    }
    public void register(SecurityUserRequest request){
        var user = User
                .builder()
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();
        user.setRoles(request.getRoles());
        userRepository.save(user);

    }
    public RefreshTokenResponse response(RefreshTokenRequest request){
        String refreshToken = request.getRefreshToken();
        return refreshTokenService.findByRefreshToken(refreshToken)
                .map(refreshTokenService::checkRefreshToken)
                .map(RefreshToken::getUserId)
                .map(userId->{
                    User ownerUser =userRepository.findById(userId)
                            .orElseThrow(()->new RefreshTokenException("Unsuccessful trying to get token for userId: "+userId));
                    String token = jwtUtils.generateTokenByUsername(ownerUser.getUsername());
                    return new RefreshTokenResponse(token,refreshTokenService.createRefreshToken(userId).getToken());
                }).orElseThrow(()->new RefreshTokenException(refreshToken,"Refresh token not found"));
    }
    public void logout(){
        var currentPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentPrincipal instanceof ApplicationUserDetails userDetails){
            Long userId = userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
        }
    }











































}
