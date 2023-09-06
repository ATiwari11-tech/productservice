package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDTO;

import java.util.List;


public interface ProductService {
    List<GenericProductDTO> getAllProducts();

    GenericProductDTO getProductById(Long id);
    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO updateProductById(GenericProductDTO product,Long id);

    GenericProductDTO deleteProductById(Long id);
}
