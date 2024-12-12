package fr.oc.chatop.web.controller;
import fr.oc.chatop.dto.MessageDTO;
import fr.oc.chatop.dto.RentalRequestDTO;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.services.RentalService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import fr.oc.chatop.entity.Rental;
import java.util.*;
@RestController
@RequestMapping("/rentals")
public class RentalsController {


    private final List<Rental> rentals = new ArrayList<>();
    private final RentalService rentalService;
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;

       }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageDTO postRental(RentalRequestDTO rentalRequestDTO) {
return rentalService.createRental(rentalRequestDTO);

    }



    @GetMapping
    public  List<RentalResponseDTO>  getRental() {
        return rentalService.getAllRentals();
    }


    @GetMapping("/{id}")
    public RentalResponseDTO getRentalById(@PathVariable Long id) {


      return rentalService.getRentalById(id);
    }

    @PutMapping(value ="/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageDTO putRental(@PathVariable Long id, RentalRequestDTO rentalRequest) {
       return rentalService.updateRental(id,rentalRequest);
    }





}
