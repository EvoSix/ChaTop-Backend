package fr.oc.chatop.mapper;


import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalResponseDTO toDto(Rental rental);
    Rental toEntity(RentalResponseDTO rentalResponseDTO);
    RentalRequestDTO entityToDto(Rental rental);
    Rental toEntityReq(RentalRequestDTO rentalRequestDTO);
}