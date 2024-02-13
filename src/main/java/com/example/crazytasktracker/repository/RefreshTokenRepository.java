package com.example.crazytasktracker.repository;

import com.example.crazytasktracker.models.entities.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RefreshTokenRepository extends CrudRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(Long userId);

}
