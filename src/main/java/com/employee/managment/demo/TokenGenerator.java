package com.employee.managment.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TokenGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(String rideId, String userId) {
        return Jwts.builder()
                .setSubject("sessionToken")
                .claim("ride", rideId)
                .claim("userId", userId)
                .claim("role", "admin")
                .claim("iss", "hopTogetherIssuer2025")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key)
                .compact();
    }

    public static Claims decodeToken(String token) {
        Claims decodedData = Jwts.parserBuilder()
                .setSigningKey(key)  // same key used for signing
                .build()
                .parseClaimsJws(token)
                .getBody();

        return decodedData;
    }
}
