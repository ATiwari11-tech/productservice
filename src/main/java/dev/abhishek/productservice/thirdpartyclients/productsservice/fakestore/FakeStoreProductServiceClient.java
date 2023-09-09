package dev.abhishek.productservice.thirdpartyclients.productsservice.fakestore;

import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Wrapper over FakeStore API
 */
@Service
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
//    private GenericProductDTO convertFakeStoreProductToGenericProduct(FakeStoreProductDTO fakeStoreProductDTO){
//        GenericProductDTO newProduct = new GenericProductDTO();
//        newProduct.setId(fakeStoreProductDTO.getId());
//        newProduct.setImage(fakeStoreProductDTO.getImage());
//        newProduct.setDescription(fakeStoreProductDTO.getDescription());
//        newProduct.setCategory(fakeStoreProductDTO.getCategory());
//        newProduct.setPrice(fakeStoreProductDTO.getPrice());
//        newProduct.setTitle(fakeStoreProductDTO.getTitle());
//        return newProduct;
//    }

    public FakeStoreProductDTO createProduct(GenericProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                createProductRequestUrl,product,FakeStoreProductDTO.class);
        return response.getBody();
    }

    public List<FakeStoreProductDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDTO>> response = restTemplate.exchange(createProductRequestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {
        });
//  or like this      ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO[].class,id);
        List<FakeStoreProductDTO> list = response.getBody();
//        List<FakeStoreProductDTO> products = new ArrayList<>();
//        for(FakeStoreProductDTO fspDto:list){
//            products.add(convertFakeStoreProductToGenericProduct(fspDto));
//        }
//        return products;
            return list;
    }

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product With Id:"+id+" doesn't exist");
        }
//        return convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO updateProductById(GenericProductDTO product,Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(getProductRequestUrl,HttpMethod.PUT,requestCallback,responseExtractor,id);
        return response.getBody();
    }

    public FakeStoreProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//      restTemplate.delete(getProductRequestUrl,id);//Since delete doesn't return anything so use low level execute method
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(getProductRequestUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
//        return convertFakeStoreProductToGenericProduct(fakeStoreProductDTO);
        return fakeStoreProductDTO;
    }
}