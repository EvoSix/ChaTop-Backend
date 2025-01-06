package fr.oc.chatop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    private Long id;
    private Long rental;
    private Long user;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String messages;

public MessageDTO(String messages) {
    this.messages = messages;
}

}
