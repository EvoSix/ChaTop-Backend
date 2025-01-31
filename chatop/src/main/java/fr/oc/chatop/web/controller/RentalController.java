package fr.oc.chatop.web.controller;

import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.services.Interfaces.IRentalService;
import fr.oc.chatop.services.RentalService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final IRentalService rentalService;






    @Operation(summary = "Create a new rental", description = "Creates a new rental with the provided details.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created successfully",
                    content = @Content(schema = @Schema(implementation = MessageResponseDTO.class)))
    })

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponseDTO postRental(@ModelAttribute RentalRequestDTO rentalRequestDTO) {

        return rentalService.createRental(rentalRequestDTO);
    }


    @Operation(summary = "Get all rentals", description = "Retrieves all the rentals available.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rentals retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RentalResponseDTO.class))))
    })

    @GetMapping
    public Map<String, List<RentalResponseDTO>> getRental() {


        Map<String, List<RentalResponseDTO>> response = new HashMap<>();
        response.put("rentals",rentalService.getAllRentals());
        return response;

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
        return rentalService.getRentalById(id);
    }


    @Operation(summary = "Update rental by ID", description = "Updates the information of a specific rental by its ID.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(schema = @Schema(implementation = MessageResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponseDTO putRental(@PathVariable Long id, RentalRequestDTO rentalRequest) {

        return rentalService.updateRental(id, rentalRequest);
    }




}
