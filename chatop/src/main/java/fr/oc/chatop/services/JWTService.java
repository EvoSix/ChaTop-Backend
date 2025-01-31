package fr.oc.chatop.services;

import fr.oc.chatop.services.Interfaces.IJWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.security.Key;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JWTService implements IJWTService {


    private final Key secretKey;
    private final Long JWT_EXPIRATION;

    public JWTService(@Value("${jwt.secret}") String secretKey,
                      @Value("${jwt.expiration}") Long jwtExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.JWT_EXPIRATION = jwtExpiration;
    }


    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(secretKey)
                .compact();

    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser().verifyWith((SecretKey) secretKey).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);

            return (username.equals(userDetails.getUsername()));
        } catch (Exception e) {
            return false;
        }
    }


    public long extractExpiration() {
        return JWT_EXPIRATION;
    }

}