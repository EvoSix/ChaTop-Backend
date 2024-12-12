package fr.oc.chatop.web.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {


    @PostMapping
    public String postMessage() {
        return "Hello World - Post Message";
    }
}
