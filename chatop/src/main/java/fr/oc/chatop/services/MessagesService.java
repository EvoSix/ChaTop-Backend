package fr.oc.chatop.services;

import fr.oc.chatop.dto.MessageDTO;
import fr.oc.chatop.dto.MessageRequestDTO;
import fr.oc.chatop.entity.Messages;
import fr.oc.chatop.entity.Rental;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.repos.MessageRepos;
import fr.oc.chatop.repos.RentalRepo;
import fr.oc.chatop.repos.UserRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessageRepos messagesRepository;


    private final UserRepos userRepository;


    private final RentalRepo rentalRepository;



    public MessageDTO createMessage(MessageRequestDTO messageRequest) {
        Messages message = new Messages();

        // Fetch the user from the database using the user ID from the request
        Optional<User> userInDB = userRepository.findById(messageRequest.getUser_id());
        if (userInDB.isPresent()) {
            message.setUser(userInDB.get());
        } else {
            throw new RuntimeException("User not found");
        }

        // Fetch the rental from the database using the rental ID from the request
        Optional<Rental> rentalInDB = rentalRepository.findById(messageRequest.getRental_id());
        if (rentalInDB.isPresent()) {
            message.setRental(rentalInDB.get());
        } else {
            throw new RuntimeException("Rental not found");
        }

        // Set message content and timestamps
        message.setMessage(messageRequest.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        // Save the message to the database
        messagesRepository.save(message);

        // Return a response object with the saved message details
        return new MessageDTO(
              "Message send with success"
        );
    }
}
