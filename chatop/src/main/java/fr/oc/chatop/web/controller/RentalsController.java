package fr.oc.chatop.web.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {


    @PostMapping
    public String postRental() {
        return "Hello World - Rental Creation";
    }



    @GetMapping
    public String getRental() {
        return "Hello World - Give Rental";
    }


    @GetMapping("/{id}")
    public String getRentalById(@PathVariable String id) {
        return "Hello World - Give Rental " + id;
    }

    @PutMapping("/{id}")
    public String putRental(@PathVariable String id) {
        return "Hello World - Update Rental " + id;
    }
}
