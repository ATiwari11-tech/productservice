package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    List<GenericProductDTO> getAllProducts();

    GenericProductDTO getProductById(UUID id) throws NotFoundException;
    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO updateProductById(GenericProductDTO product,UUID id);

    GenericProductDTO deleteProductById(UUID id);
}
