package fr.oc.chatop.web.controller;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {



    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @Operation(summary = "Get user by ID", description = "Fetches information about a user by their ID.",
            security = { @SecurityRequirement(name = "Bearer Authentication") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String authenticatedUsername = userDetails.getUsername();


            UserResponseDTO userResponseDTO = userService.getUserById(id);
            if (!userResponseDTO.getEmail().equals(authenticatedUsername)) {
                throw new RuntimeException("Access denied: You can only view your own details.");
            }

            return userResponseDTO;
        }

        throw new RuntimeException("User is not authenticated.");
    }
    }

