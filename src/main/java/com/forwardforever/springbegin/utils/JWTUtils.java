package com.forwardforever.springbegin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.forwardforever.springbegin.cache.CacheOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Map;

@Data
@Component
public class JWTUtils {

    @Value("${jwt.sign}")
    private String SIGN;
    @Value("${jwt.expire-time}")
    private Integer EXPIRE_TIME;

    private final CacheOperation cacheOperation;

    public JWTUtils(CacheOperation cacheOperation) {
        this.cacheOperation = cacheOperation;
    }


    public Integer getUserId(String token) {
        Map<String, Object> map = cacheOperation.decodedJWT(token);
        return map.get("userId").hashCode();
    }

    public Boolean isExpire(String token) {
        Map<String, Object> map = cacheOperation.decodedJWT(token);
        return map.get("exp").hashCode() < Instant.now().getEpochSecond();
    }

    public String createToken(Map<String, Object> map) {
        Calendar instance = Calendar.getInstance();
        // 设置过期时间
        instance.add(Calendar.MINUTE, EXPIRE_TIME);
        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> builder.withClaim(k, v.toString()));
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
    }
}
