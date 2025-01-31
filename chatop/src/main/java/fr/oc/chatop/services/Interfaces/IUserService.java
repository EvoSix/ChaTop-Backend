package fr.oc.chatop.services.Interfaces;

import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.dto.UserResponseDTO;

public interface IUserService {
    UserResponseDTO getUserById(Long id);
    void createUser(UserRequestDTO userRequestDTO);
}