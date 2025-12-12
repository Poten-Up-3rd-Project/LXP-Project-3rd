package com.lxp.auth.infrastructure.security.adapter;

import com.lxp.auth.domain.common.exception.TokenRevocationException;
import com.lxp.auth.domain.common.policy.TokenRevocationPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisTokenRevocationAdapter implements TokenRevocationPolicy {

    private static final String TOKEN_REVOCATION_KEY = "tokenRevocation:";
    private static final String VALUE = "revoked";

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void revokeAccessToken(String token, long durationSeconds) {
        if (isTokenExpired(durationSeconds)) {
            log.warn("Attempted to revoke token with non-positive duration: {}", durationSeconds);
            return;
        }

        String key = createKey(token);
        try {
            redisTemplate.opsForValue().set(key, VALUE, durationSeconds, TimeUnit.SECONDS);
            log.info("Access Token revoked successfully. Key: {}, TTL: {} seconds", key, durationSeconds);
        } catch (Exception e) {
            log.error("Failed to revoke access token in Redis. Key: {}", key, e);
            throw new TokenRevocationException("Failed to revoke access token in Redis.");
        }
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        String key = createKey(token);
        boolean result = false;

        try {
            Boolean exists = redisTemplate.hasKey(key);

            if (Objects.nonNull(exists) && exists) {
                log.info("Token is blacklisted. Key: {}", key);
                result = true;
            }
        } catch (Exception e) {
            log.error("Failed to check token blacklist status in Redis. Key: {}", key, e);
        }

        return result;
    }

    private String createKey(String token) {
        return TOKEN_REVOCATION_KEY + token;
    }

    private boolean isTokenExpired(long durationSeconds) {
        return durationSeconds <= 0;
    }
}
