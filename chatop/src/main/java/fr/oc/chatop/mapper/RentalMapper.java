package fr.oc.chatop.mapper;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    @Mapping(target = "owner_id", source = "owner.id")
    RentalResponseDTO toDto(Rental rental);
    Rental toEntity(RentalResponseDTO rentalResponseDTO);
    @Mapping(target = "picture", source = "picture", qualifiedByName = "stringToMultipartFile")
    RentalRequestDTO entityToDto(Rental rental);

    @Mapping(target = "picture", source = "picture", qualifiedByName = "multipartFileToString")
    Rental toEntityReq(RentalRequestDTO rentalRequestDTO);

    @Named("stringToMultipartFile")
    default MultipartFile mapStringToMultipartFile(String value) {
        return null;
    }
    @Named("multipartFileToString")
    default String mapMultipartFileToString(MultipartFile file) {
        return file != null ? file.getOriginalFilename() : null;
    }
}