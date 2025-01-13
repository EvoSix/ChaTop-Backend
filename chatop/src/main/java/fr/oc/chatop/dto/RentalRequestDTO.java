package fr.oc.chatop.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalRequestDTO {

    private String name;


    private Long surface;


    private Double price;


    private MultipartFile picture;


    private String description;


}
