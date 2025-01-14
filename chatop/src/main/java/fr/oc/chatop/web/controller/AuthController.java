package fr.oc.chatop.web.controller;
import fr.oc.chatop.dto.AuthResponseDTO;
import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.repos.UserRepos;
import fr.oc.chatop.services.AuthService;
import fr.oc.chatop.services.JWTService;
import fr.oc.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.security.AuthProvider;

import java.util.*;

@RestController
@RequestMapping("auth")
public class AuthController {



    private final AuthService authService;
    private final UserService userService;


    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(AuthService authService, UserService userService, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userService = userService;


        this.passwordEncoder = passwordEncoder;
    }




    @Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation =String.class)))
    })
    @PostMapping("/register")
    public AuthResponseDTO postAuth(@RequestBody UserRequestDTO userRequestDTO) {


userService.createUser(userRequestDTO);
        //création de jwt
        return authService.login(userRequestDTO);

    }
    @Operation(summary = "User login", description = "Authenticates a user with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                    content = @Content(schema = @Schema(implementation = AuthResponseDTO.class))),

    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> postLogin(@RequestBody UserRequestDTO userRequestDTO) {
       AuthResponseDTO authResponse = authService.login(userRequestDTO);
        return ResponseEntity.ok(authResponse);
    }




    @Operation(summary = "Get authenticated user info", description = "Retrieves information about the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User info retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })

    @GetMapping("/me")
    public UserResponseDTO getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if user is authenticated and retrieve user details
        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new RuntimeException("User not found"); // Throw error if user is not found
        }

        // Return the authenticated user's information
        return new UserResponseDTO(user.getId().intValue(), user.getName(), user.getEmail(), user.getCreated_at(), user.getUpdated_at());
    }
}
