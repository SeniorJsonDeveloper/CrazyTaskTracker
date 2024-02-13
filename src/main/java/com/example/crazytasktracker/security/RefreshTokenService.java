package com.example.crazytasktracker.security;

import com.example.crazytasktracker.models.entities.RefreshToken;
import com.example.crazytasktracker.exception.RefreshTokenException;
import com.example.crazytasktracker.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${app.jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;
    private final RefreshTokenRepository repository;

    public Optional<RefreshToken> findByRefreshToken(String token){
        return repository.findByToken(token);
    }
    public RefreshToken createRefreshToken(Long userId){
        var refreshToken = RefreshToken.builder()
                .userId(userId)
                .expireDate(Instant.now().plusMillis(refreshTokenExpiration.toMillis()))
                .token(UUID.randomUUID().toString())
                .build();
       return repository.save(refreshToken);
    }
    public RefreshToken checkRefreshToken(RefreshToken refreshToken){
        if (refreshToken.getExpireDate().compareTo(Instant.now())<0){
            repository.delete(refreshToken);
            throw new RefreshTokenException(refreshToken.getToken(),"Refresh token was expired. Repeat signin action");
        }
        return refreshToken;
    }
    public void deleteByUserId(Long userId){
        repository.deleteByUserId(userId);
    }




























}
