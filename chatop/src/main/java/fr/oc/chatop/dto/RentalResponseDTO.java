package fr.oc.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {
    private Long id;
    private String name;
    private Long surface;
    private Double price;
    private String picture;
    private String description;
    private Long owner_id;
    private String created_at;
    private String updated_at;

}
