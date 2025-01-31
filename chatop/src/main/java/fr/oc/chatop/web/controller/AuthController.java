package fr.oc.chatop.web.controller;
import fr.oc.chatop.dto.AuthResponseDTO;
import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entities.User;


import fr.oc.chatop.services.Interfaces.IAuthService;
import fr.oc.chatop.services.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@RestController
@RequestMapping("auth")

@RequiredArgsConstructor
public class AuthController {



    private final IAuthService authService;
    private final IUserService userService;







    @Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation =String.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> postAuth(@Valid @RequestBody UserRequestDTO userRequestDTO)  {

Optional <User> User= userService.createUser(userRequestDTO);
if(User.isPresent()){
   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
        Optional<AuthResponseDTO> authResponse = authService.login(userRequestDTO);
        return authResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }
    @Operation(summary = "User login", description = "Authenticates a user with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                    content = @Content(schema = @Schema(implementation = AuthResponseDTO.class))),

    })
    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody UserRequestDTO userRequestDTO) {
      // AuthResponseDTO authResponse = authService.login(userRequestDTO);
   Optional<AuthResponseDTO> authResponse = authService.login(userRequestDTO);


        if (authResponse.isPresent()) {
            return ResponseEntity.ok(authResponse.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponseDTO("Error"));
        }
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
        return new UserResponseDTO(user.getId().intValue(), user.getName(), user.getEmail(), user.getCreated_at().toString(), user.getUpdated_at().toString());
    }
}
