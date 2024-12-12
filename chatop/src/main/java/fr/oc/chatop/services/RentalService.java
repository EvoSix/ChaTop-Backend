package fr.oc.chatop.services;
import fr.oc.chatop.dto.MessageDTO;
import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.entity.Rental;
import fr.oc.chatop.mapper.RentalMapper;
import fr.oc.chatop.repos.RentalRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {



    private final RentalRepo rentalRepos;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepo rentalRepos, RentalMapper rentalMapper) {
        this.rentalRepos = rentalRepos;
        this.rentalMapper = rentalMapper;
    }

    public List<RentalResponseDTO> getAllRentals() {
        return rentalRepos.findAll().stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    public RentalResponseDTO getRentalById(Long id) {
        Rental rental = rentalRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        return rentalMapper.toDto(rental);
    }

    public MessageDTO createRental(RentalRequestDTO rentalResponseDTO) {

        try{
        Rental rental = rentalMapper.toEntityReq(rentalResponseDTO);
         rentalRepos.save(rental);
        }catch (RuntimeException e){
            return new MessageDTO(e.getMessage());
        }
        return new MessageDTO("Rental created");
    }

    public MessageDTO updateRental(Long id, RentalRequestDTO rentalResponseDTO) {
        Rental rental = rentalRepos.findById(id)
                        .orElse(new Rental());
if(rental.getId()==null){
    return new MessageDTO("Rental not found");

}
        rental.setName(rentalResponseDTO.getName());
        rental.setSurface(rentalResponseDTO.getSurface());
        rental.setPrice(rentalResponseDTO.getPrice());
 //       rental.setPicture(rentalResponseDTO.getPicture());
        rental.setDescription(rentalResponseDTO.getDescription());

        try {

            rentalRepos.save(rental);
            return new MessageDTO("Rental updated successfully");
        } catch (Exception e) {

            return new MessageDTO("An error occurred while updating the rental: " + e.getMessage());
        }
    }


    public void deleteRental(Long id) {
        Rental rental = rentalRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        rentalRepos.delete(rental);
    }
}
