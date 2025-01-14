package fr.oc.chatop.services;

import fr.oc.chatop.dto.AuthResponseDTO;
import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepos userRepos;
    private final JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthService(UserRepos userRepos, JWTService jwtService) {
        this.userRepos = userRepos;
        this.jwtService = jwtService;

    }

    public AuthResponseDTO login(UserRequestDTO userRequestDTO) {


        User user = userRepos.findByEmail(userRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

//        if (!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid email or password");
//        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequestDTO.getEmail(),
                        userRequestDTO.getPassword()
                )
        );


        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDTO(token, jwtService.extractExpiration());
    }
}