package com.example.login_study.demo.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey securityKey;
    private final long expirationTime = 1000 * 60 * 60;

    public JwtUtil(@Value("${jwt.secret-key}") String secret){
        this.securityKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Long getMemberId(String token){
        return Jwts.parser()
                .verifyWith(securityKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("memberId", long.class);
    }

    public String createToken(Long memberId){
        return Jwts.builder()
                .claim("memberId",memberId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(securityKey)
                .compact();
    }

    public Boolean isTokenExpired(String token){
        return Jwts.parser()
                .verifyWith(securityKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date(System.currentTimeMillis()));
    }
}
