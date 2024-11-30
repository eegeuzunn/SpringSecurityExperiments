package com.JwtAuthentication.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SEC_KEY;

    public String generateAccessToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 min
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateToken(String token, String username){
        Date expirationDate = extractExpirationDate(token);
        String tokenUserName = extractUsername(token);
        return expirationDate.after(new Date( System.currentTimeMillis())) && username.equals(tokenUserName);
    }

    private Date extractExpirationDate(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getExpiration();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SEC_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
