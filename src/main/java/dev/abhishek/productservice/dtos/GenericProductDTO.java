package dev.abhishek.productservice.dtos;

import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
public class GenericProductDTO {
//    private Long id;
//    private String title;
//    private String description;
//    private String image;//url of the image
//    private String category;
//    private double price;

    private UUID id;
    private String title;
    private String description;
    private String image;//url of the image
    private Category category;
    private Price price;
}
