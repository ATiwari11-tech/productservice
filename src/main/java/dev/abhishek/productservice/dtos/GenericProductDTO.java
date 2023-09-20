package dev.abhishek.productservice.dtos;

import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;//url of the image
    private Category category;
    private Price price;
}
