package fr.oc.chatop.services.Interfaces;

import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import java.util.List;

public interface IRentalService {
    List<RentalResponseDTO> getAllRentals();
    RentalResponseDTO getRentalById(Long id);
    MessageResponseDTO createRental(RentalRequestDTO rentalResponseDTO);
    MessageResponseDTO updateRental(Long id, RentalRequestDTO rentalResponseDTO);
    void deleteRental(Long id);
}