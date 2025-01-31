package fr.oc.chatop.web.controller;

import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.MessageRequestDTO;
import fr.oc.chatop.services.Interfaces.IMessagesService;
import fr.oc.chatop.services.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessagesController {

    private final IMessagesService messagesService;



    @Operation(summary = "Create a message", description = "Creates a new message related to a specific rental.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created successfully",
                    content = @Content(schema = @Schema(implementation = MessageResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(hidden = true)))
    })

    @PostMapping
    public ResponseEntity<?> PostMessage(@RequestBody MessageRequestDTO messageRequest) {

        Optional<MessageResponseDTO> MessageDTO= messagesService.createMessage(messageRequest);
        if (MessageDTO.isPresent()) {
            return ResponseEntity.ok(MessageDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }
}
