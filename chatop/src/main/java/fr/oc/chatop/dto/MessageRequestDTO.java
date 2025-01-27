package fr.oc.chatop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MessageRequestDTO {

    private Long rental_id;


    private Long user_id;


    private String message;
}
