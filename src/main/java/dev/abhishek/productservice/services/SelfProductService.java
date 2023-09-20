package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO product, Long id) {
        return null;
    }


    @Override
    public GenericProductDTO deleteProductById(Long id) {
        return null;
    }


}
