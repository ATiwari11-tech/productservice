package dev.abhishek.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;
    private int pageNumber;
    private int sizeOfEachPage;
}