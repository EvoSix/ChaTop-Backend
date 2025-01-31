package fr.oc.chatop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponseDTO {

    private String message;

public MessageResponseDTO(String messages) {
    this.message = messages;
}

}
