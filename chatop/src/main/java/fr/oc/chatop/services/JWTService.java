package fr.oc.chatop.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JWTService {



    private final Key secretKey;
    private final Long JWT_EXPIRATION;

    public JWTService(@Value("${jwt.secret}") String secretKey,
                      @Value("${jwt.expiration}") Long jwtExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.JWT_EXPIRATION = jwtExpiration;
    }

    public void init() {

    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
                .signWith(secretKey)
                .compact();

    }
    public String extractUsername(String token){
        Claims claims =    Jwts.parser().verifyWith((SecretKey) secretKey).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);

            return (username.equals(userDetails.getUsername()) );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().toString());
            return false;
        }
    }

    public String extractEmail(String token) { //MAUVAIS
        return Jwts.parser()

                .decryptWith((SecretKey) secretKey)
                .build().parseSignedClaims(token).getPayload().getSubject();
    }


    public long extractExpiration() {
        return JWT_EXPIRATION;
    }

}