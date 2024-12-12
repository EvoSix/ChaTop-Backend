package fr.oc.chatop.web.controller;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {



    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {

            return userService.getRentalById(id);


    }
}
