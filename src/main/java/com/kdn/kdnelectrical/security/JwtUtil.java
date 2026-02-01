package com.kdn.kdnelectrical.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Token validity: 24 hours
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000;

    // Secret key (generated once per app start)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ========================
    // Generate JWT Token
    // ========================
    public String generateToken(String subject, String role) {
        return Jwts.builder()
                .setSubject(subject) // phone
                .claim("role", role) // ROLE_CUSTOMER / ROLE_WORKER / ROLE_ADMIN
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key)
                .compact();
    }
    
    // ========================
    // Extract Role
    // ========================
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }


    // ========================
    // Extract subject (phone)
    // ========================
    public String extractSubject(String token) {
        return getClaims(token).getSubject();
    }

    // ========================
    // Validate token
    // ========================
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ========================
    // Internal: parse claims
    // ========================
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
