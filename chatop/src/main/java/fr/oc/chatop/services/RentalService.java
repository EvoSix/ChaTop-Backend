package fr.oc.chatop.services;
import fr.oc.chatop.dto.MessageResponseDTO;
import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.entities.Rental;
import fr.oc.chatop.entities.User;
import fr.oc.chatop.mapper.RentalMapper;
import fr.oc.chatop.repositories.RentalRepository;
import fr.oc.chatop.repositories.UserRepository;
import fr.oc.chatop.services.Interfaces.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService implements IRentalService {


    private final UserRepository userRepository;

    private final RentalRepository rentalRepos;
    private final RentalMapper rentalMapper;



    public List<RentalResponseDTO> getAllRentals() {



        return rentalRepos.findAll().stream()
                .map(rentalMapper::toDto).toList();
    }

    public RentalResponseDTO getRentalById(Long id) {
        Rental rental = rentalRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        return rentalMapper.toDto(rental);
    }

    public MessageResponseDTO createRental(RentalRequestDTO rentalResponseDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User userDetails = (User) auth.getPrincipal();

        Long authenticatedID = userDetails.getId();


        Optional<User> userInDB = userRepository.findById(authenticatedID);
        if (userInDB.isEmpty()) {
            throw new RuntimeException("Access denied: Unauthorized User");
        }


        try{
        Rental rental = rentalMapper.toEntityReq(rentalResponseDTO);
            rental.setOwner(userInDB.get());
            MultipartFile picture = rentalResponseDTO.getPicture();
            if (picture != null && !picture.isEmpty()) {
                String imageUrl = saveImageToServer(picture);
                rental.setPicture(imageUrl);
            } else {
                rental.setPicture("https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg");
            }
            rental.setCreated_at(LocalDateTime.now());
            rental.setUpdated_at(LocalDateTime.now());
         rentalRepos.save(rental);
        }catch (RuntimeException | IOException e){


            return new MessageResponseDTO(e.getMessage());
        }
        return new MessageResponseDTO("Rental created");
    }

    public MessageResponseDTO updateRental(Long id, RentalRequestDTO rentalResponseDTO) {
        Rental rental = rentalRepos.findById(id)
                        .orElse(new Rental());
if(rental.getId()==null){
    return new MessageResponseDTO("Rental not found");

}

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();

        if (!rental.getOwner().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to modify this rental");
        }

        rental.setName(rentalResponseDTO.getName());
        rental.setSurface(rentalResponseDTO.getSurface());
        rental.setPrice(rentalResponseDTO.getPrice());

        rental.setDescription(rentalResponseDTO.getDescription());

        try {

            rentalRepos.save(rental);
            return new MessageResponseDTO("Rental updated successfully");
        } catch (Exception e) {

            return new MessageResponseDTO("An error occurred while updating the rental: " + e.getMessage());
        }
    }


    public void deleteRental(Long id) {
        Rental rental = rentalRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        rentalRepos.delete(rental);
    }



    private String saveImageToServer(MultipartFile file) throws IOException {

        String uploadDir = "C:/wamp64/www/uploads/";


        String uniqueFilename = System.currentTimeMillis() + "_" + file.getOriginalFilename();


        File destinationFile = new File(uploadDir + uniqueFilename);
        file.transferTo(destinationFile);


        return "http://localhost/uploads/" + uniqueFilename;
    }

}
