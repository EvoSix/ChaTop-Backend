package fr.oc.chatop.web.controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public String postAuth() {
        return "Hello World - Register";
    }

    @PostMapping("/login")
    public String postLogin() {
        return "Hello World - Login";
    }

    @GetMapping("/me")
    public String getMe() {
        return "Hello World - Me";
    }
}
