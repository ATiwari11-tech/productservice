package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;

import java.util.List;

public interface FakeProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
}
