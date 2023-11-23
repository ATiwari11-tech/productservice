package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDto;
import dev.abhishek.productservice.models.Product;
import dev.abhishek.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<GenericProductDto> searchProducts(String query, int queryPageNumber, int querySizeOfEachPage) {
        Page<Product> productPage = productRepository.findAllByTitleContaining(query, PageRequest.of(queryPageNumber, querySizeOfEachPage));
        List<Product> products = productPage.get().collect(Collectors.toList());
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(Product product:products){
            genericProductDtos.add(GenericProductDto.from(product));
        }
        Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(
                genericProductDtos,productPage.getPageable(),productPage.getTotalElements()
        );
        return genericProductDtoPage;
    }
}
