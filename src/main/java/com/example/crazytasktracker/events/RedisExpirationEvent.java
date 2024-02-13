package com.example.crazytasktracker.events;

import com.example.crazytasktracker.exception.RefreshTokenException;
import com.example.crazytasktracker.models.entities.RefreshToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisExpirationEvent {
    @EventListener
    public void handleRedisKetExpiredEvent(RedisKeyExpiredEvent<RefreshToken> event){
        RefreshToken refreshToken = (RefreshToken) event.getValue();
        if (refreshToken==null){
            throw new RefreshTokenException("Refresh token is null");
        }
    }
}
