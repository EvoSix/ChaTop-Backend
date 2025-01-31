package fr.oc.chatop.services.Interfaces;

import fr.oc.chatop.dto.MessageRequestDTO;
import fr.oc.chatop.dto.MessageResponseDTO;

import java.util.Optional;

public interface IMessagesService {
    Optional<MessageResponseDTO> createMessage(MessageRequestDTO messageRequest);
}