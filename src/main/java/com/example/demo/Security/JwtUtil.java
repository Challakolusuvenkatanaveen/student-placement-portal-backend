package com.example.demo.Security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final long EXPIRATION =
            1000 * 60 * 60 * 24;

    // =============================
    // Get Secret Key
    // =============================

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // =============================
    // Generate Token
    // =============================

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION
                        )
                )
                .signWith(
                        getKey(),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    // =============================
    // Extract Username
    // =============================

    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    // =============================
    // Extract Expiration
    // =============================

    public Date extractExpiration(String token) {

        return extractClaims(token).getExpiration();
    }

    // =============================
    // Extract Claims
    // =============================

    private Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // =============================
    // Check Expired
    // =============================

    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    // =============================
    // Validate Token
    // =============================

    public boolean validateToken(
            String token,
            String email) {

        return extractUsername(token)
                .equals(email)
                && !isTokenExpired(token);
    }
}