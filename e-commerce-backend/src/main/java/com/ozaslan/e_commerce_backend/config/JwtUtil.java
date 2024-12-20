package com.ozaslan.e_commerce_backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Date;

@Configuration
public class JwtUtil {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Rastgele oluşturulmuş güvenli key
    private final long expirationTime = 1000 * 60 * 60 * 10; // 10 saat

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey) // Rastgele oluşturulmuş key kullanıldı
                .compact();
    }


    public String extractEmailFromResetToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

}
