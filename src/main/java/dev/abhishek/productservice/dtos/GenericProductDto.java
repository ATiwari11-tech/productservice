package dev.abhishek.productservice.dtos;

import dev.abhishek.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public static GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
//        genericProductDto.setPrice(product.getPrice().getPrice());
        return genericProductDto;
    }
}
