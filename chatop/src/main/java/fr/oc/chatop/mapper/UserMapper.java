package fr.oc.chatop.mapper;

import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    UserResponseDTO toDto(User user);

}