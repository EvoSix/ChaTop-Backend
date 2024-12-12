package fr.oc.chatop.dto;
import org.springframework.web.multipart.MultipartFile;
public class RentalRequestDTO {

    private String name;


    private Long surface;


    private Double price;


    private MultipartFile picture;


    private String description;

    public RentalRequestDTO() {
    }

    public RentalRequestDTO(String name, Long surface, Double price, MultipartFile picture, String description) {

        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSurface() {
        return surface;
    }

    public void setSurface(Long surface) {
        this.surface = surface;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
