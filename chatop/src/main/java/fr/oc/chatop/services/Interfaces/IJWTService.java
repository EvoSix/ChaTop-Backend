package fr.oc.chatop.services.Interfaces;


import org.springframework.security.core.userdetails.UserDetails;

public interface IJWTService {
    String generateToken(String email);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
    long extractExpiration();
}