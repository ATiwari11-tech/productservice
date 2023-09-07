package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.exceptions.NotFoundException;

import java.util.List;


public interface ProductService {
    List<GenericProductDTO> getAllProducts();

    GenericProductDTO getProductById(Long id) throws NotFoundException;
    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO updateProductById(GenericProductDTO product,Long id);

    GenericProductDTO deleteProductById(Long id);
}
