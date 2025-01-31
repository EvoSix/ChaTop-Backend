package fr.oc.chatop.services.Interfaces;

import fr.oc.chatop.dto.AuthResponseDTO;
import fr.oc.chatop.dto.UserRequestDTO;

import java.util.Optional;

public interface IAuthService {
    Optional<AuthResponseDTO> login(UserRequestDTO userRequestDTO);
}