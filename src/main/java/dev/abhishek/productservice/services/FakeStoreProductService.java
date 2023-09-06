package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.FakeStoreProductDTO;
import dev.abhishek.productservice.dtos.GenericProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDTO createProduct(GenericProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(
                createProductRequestUrl,product,GenericProductDTO.class);
        return response.getBody();
    }

    @Override
    public List<GenericProductDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDTO>> response = restTemplate.exchange(createProductRequestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {
        });
        List<FakeStoreProductDTO> list = response.getBody();
        List<GenericProductDTO> products = new ArrayList<>();
        for(FakeStoreProductDTO fspDto:list){
            GenericProductDTO product = new GenericProductDTO();
            product.setId(fspDto.getId());
            product.setImage(fspDto.getImage());
            product.setTitle(fspDto.getTitle());
            product.setPrice(fspDto.getPrice());
            product.setCategory(fspDto.getCategory());
            product.setDescription(fspDto.getDescription());
            products.add(product);
        }
        return products;
    }
    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        GenericProductDTO product = new GenericProductDTO();
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());
        return product;
    }
    @Override
    public GenericProductDTO updateProductById(GenericProductDTO product,Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(getProductRequestUrl,product,id);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        GenericProductDTO newProduct = new GenericProductDTO();
        newProduct.setImage(fakeStoreProductDTO.getImage());
        newProduct.setDescription(fakeStoreProductDTO.getDescription());
        newProduct.setCategory(fakeStoreProductDTO.getCategory());
        newProduct.setPrice(fakeStoreProductDTO.getPrice());
        newProduct.setTitle(fakeStoreProductDTO.getTitle());
        return newProduct;
    }
    @Override
    public GenericProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(getProductRequestUrl,id);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        GenericProductDTO product = new GenericProductDTO();
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());
        return product;
    }
}
