package fr.oc.chatop.web.controller;

import fr.oc.chatop.dto.MessageDTO;
import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.services.RentalService;
import fr.oc.chatop.services.UserService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import fr.oc.chatop.entity.Rental;

import java.util.*;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private final UserService userService;
    private final List<Rental> rentals = new ArrayList<>();
    private final RentalService rentalService;



    public RentalsController(RentalService rentalService , UserService userService) {
        this.rentalService = rentalService;

        this.userService = userService;
    }


    @Operation(summary = "Create a new rental", description = "Creates a new rental with the provided details.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created successfully",
                    content = @Content(schema = @Schema(implementation = MessageDTO.class)))
    })

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageDTO postRental(RentalRequestDTO rentalRequestDTO) {
        AuthanticateUser();
        return rentalService.createRental(rentalRequestDTO);
    }


    @Operation(summary = "Get all rentals", description = "Retrieves all the rentals available.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rentals retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RentalResponseDTO.class))))
    })

    @GetMapping
    public List<RentalResponseDTO> getRental() {


        AuthanticateUser();
        return rentalService.getAllRentals();

    }

    @Operation(summary = "Get rental by ID", description = "Fetches rental information for a specific rental by its ID.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental found",
                    content = @Content(schema = @Schema(implementation = RentalResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    @GetMapping("/{id}")
    public RentalResponseDTO getRentalById(@PathVariable Long id) {

        AuthanticateUser();

        return rentalService.getRentalById(id);
    }


    @Operation(summary = "Update rental by ID", description = "Updates the information of a specific rental by its ID.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(schema = @Schema(implementation = MessageDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageDTO putRental(@PathVariable Long id, RentalRequestDTO rentalRequest) {
        AuthanticateUser();
        return rentalService.updateRental(id, rentalRequest);
    }


    private void AuthanticateUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User userDetails = (User) auth.getPrincipal();
        String authenticatedUsername = userDetails.getName();
        Long authenticatedID = userDetails.getId();


        UserResponseDTO userResponseDTO = userService.getUserById(authenticatedID);
        if (!userResponseDTO.getName().equals(authenticatedUsername)) {
            throw new RuntimeException("Access denied: Unauthorized User");
        }
    }


}
