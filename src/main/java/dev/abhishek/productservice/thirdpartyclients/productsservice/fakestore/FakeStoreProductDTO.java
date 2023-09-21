package dev.abhishek.productservice.thirdpartyclients.productsservice.fakestore;

import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class FakeStoreProductDTO {
//    private Long id;
//    private String title;
//    private double price;
//    private String category;
//    private String description;
//    private String image;

    private UUID id;
    private String title;
    private Price price;
    private Category category;
    private String description;
    private String image;
}
