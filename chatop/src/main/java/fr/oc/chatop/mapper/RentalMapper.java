package fr.oc.chatop.mapper;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.entities.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    @Mapping(target = "owner_id", source = "owner.id")
    RentalResponseDTO toDto(Rental rental);



    @Mapping(target = "picture", source = "picture", qualifiedByName = "multipartFileToString")
    Rental toEntityReq(RentalRequestDTO rentalRequestDTO);


    @Named("multipartFileToString")
    default String mapMultipartFileToString(MultipartFile file) {
        return file != null ? file.getOriginalFilename() : null;
    }
}