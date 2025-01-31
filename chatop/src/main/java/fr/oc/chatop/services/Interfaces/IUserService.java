package fr.oc.chatop.services.Interfaces;

import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entities.User;
import org.apache.coyote.BadRequestException;

import java.util.Optional;

public interface IUserService {
    UserResponseDTO getUserById(Long id);
    Optional<User> createUser(UserRequestDTO userRequestDTO) ;
}