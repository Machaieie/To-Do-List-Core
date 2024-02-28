package com.todolistcore.ToDoListCore.services;


import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.todolistcore.ToDoListCore.model.User;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

@Service
public class JwtService {

    private final String SECRECT_KEY = "f9871c58f5f65627496830ff03fb971eacfbc7426ee26a3dd50c28b0f6579b82";

    public String extractUsername(String tocken){
        return extractClaim(tocken, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String generateToken(User user){
        String token = Jwts.builder()
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration( new Date(System.currentTimeMillis()+24*60*60*1000) )
        .signWith(getSignKey()).compact();

        return token;
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
        .verifyWith(getSignKey())
        .build().parseSignedClaims(token)
        .getPayload();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRECT_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}  
