package fr.oc.chatop.services;

import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.MessageRequestDTO;
import fr.oc.chatop.entities.Messages;
import fr.oc.chatop.entities.Rental;
import fr.oc.chatop.entities.User;
import fr.oc.chatop.repositories.MessagesRepository;
import fr.oc.chatop.repositories.RentalRepository;
import fr.oc.chatop.repositories.UserRepository;
import fr.oc.chatop.services.Interfaces.IMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessagesService implements IMessagesService {

    private final MessagesRepository messagesRepository;


    private final UserRepository userRepository;


    private final RentalRepository rentalRepository;


    public Optional<MessageResponseDTO> createMessage(MessageRequestDTO messageRequest) {
        Messages message = new Messages();

        // Fetch the user from the database using the user ID from the request
        Optional<User> userInDB = userRepository.findById(messageRequest.getUser_id());
        if (userInDB.isPresent()) {
            message.setUser(userInDB.get());
        } else {

            return Optional.empty();
        }

        // Fetch the rental from the database using the rental ID from the request
        Optional<Rental> rentalInDB = rentalRepository.findById(messageRequest.getRental_id());
        if (rentalInDB.isPresent()) {
            message.setRental(rentalInDB.get());
        } else {

            return Optional.empty();
        }
        if (messageRequest.getMessage().isEmpty()) {

            return Optional.empty();
        }
        // Set message content and timestamps
        message.setMessage(messageRequest.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        // Save the message to the database
        messagesRepository.save(message);

        // Return a response object with the saved message details
        return Optional.of(new MessageResponseDTO(
                "Message send with success")
        );
    }
}
