package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.FakeStoreProductDTO;
import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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

    private GenericProductDTO convertFakeStoreProductToGenericProduct(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO newProduct = new GenericProductDTO();
        newProduct.setId(fakeStoreProductDTO.getId());
        newProduct.setImage(fakeStoreProductDTO.getImage());
        newProduct.setDescription(fakeStoreProductDTO.getDescription());
        newProduct.setCategory(fakeStoreProductDTO.getCategory());
        newProduct.setPrice(fakeStoreProductDTO.getPrice());
        newProduct.setTitle(fakeStoreProductDTO.getTitle());
        return newProduct;
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
//  or like this      ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO[].class,id);
        List<FakeStoreProductDTO> list = response.getBody();
        List<GenericProductDTO> products = new ArrayList<>();
        for(FakeStoreProductDTO fspDto:list){
            products.add(convertFakeStoreProductToGenericProduct(fspDto));
        }
        return products;
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product With Id:"+id+" doesn't exist");
        }
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);
    }
    @Override
    public GenericProductDTO updateProductById(GenericProductDTO product,Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDTO.class);
        ResponseExtractor<ResponseEntity<GenericProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(GenericProductDTO.class);
        ResponseEntity<GenericProductDTO> response = restTemplate.execute(getProductRequestUrl,HttpMethod.PUT,requestCallback,responseExtractor,id);
        return response.getBody();
    }
    @Override
    public GenericProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//      restTemplate.delete(getProductRequestUrl,id);//Since delete doesn't return anything so use low level execute method
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(getProductRequestUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);
    }
}
