package com.forwardforever.springbegin.cache;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheOperation {

    @Value("${jwt.sign}")
    private String SIGN;

    // 需要缓存结果
    @Cacheable(value = "jwtCache", key = "#token", sync = true)
    public Map<String, Object> decodedJWT(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", verify.getClaim("userId").as(Integer.class));
        map.put("userName", verify.getClaim("userName").as(String.class));
        map.put("exp", verify.getClaim("exp").as(Long.class));
        return map;
    }
}
