package fr.oc.chatop.web.controller;

import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.MessageRequestDTO;
import fr.oc.chatop.services.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
//@RequiredArgsConstructor
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Operation(summary = "Create a message", description = "Creates a new message related to a specific rental.",
            security = {@SecurityRequirement(name = "Bearer Authentication")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created successfully",
                    content = @Content(schema = @Schema(implementation = MessageResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(hidden = true)))
    })

    @PostMapping
    public MessageResponseDTO createUser(@RequestBody MessageRequestDTO messageRequest) {

        return messagesService.createMessage(messageRequest); // Create and return the new message
    }
}
