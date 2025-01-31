package fr.oc.chatop.services;

import fr.oc.chatop.dto.AuthResponseDTO;
import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.entities.User;
import fr.oc.chatop.repositories.UserRepository;
import fr.oc.chatop.services.Interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepos;
    private final JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepos, JWTService jwtService) {
        this.userRepos = userRepos;
        this.jwtService = jwtService;

    }

    public Optional<AuthResponseDTO> login(UserRequestDTO userRequestDTO) {


        Optional<User> user = userRepos.findByEmail(userRequestDTO.getEmail());

        if (user.isEmpty()) {
            return Optional.empty();
        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequestDTO.getEmail(),
                        userRequestDTO.getPassword()
                )
        );

        User userEntity = user.get();
        String token = jwtService.generateToken(userEntity.getEmail());
        return Optional.of(new AuthResponseDTO(token));
    }
}