package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.ProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.models.Product;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(Product product);

    ProductDto getProductById(String id) throws NotFoundException;
    List<ProductDto> getProductsInCategory(String id) throws NotFoundException;

    List<ProductDto> getAllProducts(List<String> categories);

    ProductDto deleteProduct(String id);
    ProductDto updateProduct(ProductDto productDto,String id);
}
