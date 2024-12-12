package fr.oc.chatop.mapper;

import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(User user);
    User toEntity(UserResponseDTO userResponseDTO);
}