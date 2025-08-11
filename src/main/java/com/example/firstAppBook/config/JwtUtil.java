package com.example.firstAppBook.config;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET ="supersecretkeythatissupposedtobe32bytes!!";// 32 bytes secret key
    private final long EXPIRATION_TIME = 1000 * 60 *60; //1 hour
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer("FirstAppBook")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }
  /*  public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("FirstAppBook")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }*/
// Method to extract username from the token

    public String extractUsername(String token){
        try {
            return extractAllClaims(token).getSubject();
        } catch (JwtException e) {
            return null; // Invalid token
        }
    }
    public String extractUserRole(String token){
        try{
            return extractAllClaims(token).get("role", String.class);
        }catch (JwtException e){
            return null; // Invalid token
        }
    }
    // Method to validate the token
    // Returns true if the token is valid, false otherwise
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true; // Token is valid
        } catch (JwtException e) {
            return false; // Token is invalid
        }
    }
        private Claims extractAllClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
}

