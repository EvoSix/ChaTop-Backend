package fr.oc.chatop.web.controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        return "Hello World - Give User " + id;
    }
}
