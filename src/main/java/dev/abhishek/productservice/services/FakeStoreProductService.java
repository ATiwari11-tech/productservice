package dev.abhishek.productservice.services;

import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDTO;
import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
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
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDTO> getAllProducts(){
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductServiceClient.getAllProducts()){
            genericProductDTOS.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDTO));
        }
        return genericProductDTOS;
    }
    @Override
    public GenericProductDTO getProductById(UUID id) throws NotFoundException {
       return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }
    @Override
    public GenericProductDTO updateProductById(GenericProductDTO product,UUID id) {
       return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.updateProductById(product,id));
    }
    @Override
    public GenericProductDTO deleteProductById(UUID id) {
      return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
    }
}
