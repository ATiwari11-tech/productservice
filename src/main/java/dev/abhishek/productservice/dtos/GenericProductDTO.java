package dev.abhishek.productservice.dtos;

import dev.abhishek.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;//url of the image
    private String category;
    private double price;
}
