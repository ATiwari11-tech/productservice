package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.models.Price;
import dev.abhishek.productservice.models.Product;
import dev.abhishek.productservice.repository.ProductRepository;
import dev.abhishek.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Service("selfProductServiceImpl")
@NoArgsConstructor
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(Product product:products){
            GenericProductDTO productDTO = new GenericProductDTO();
            Category category = new Category();
            category.setUuid(product.getCategory().getUuid());
            category.setName(product.getCategory().getName());
            Price price = new Price();
            price.setUuid(product.getPrice().getUuid());
            price.setPrice(product.getPrice().getPrice());
            price.setCurrency(product.getPrice().getCurrency());
            productDTO.setId(product.getUuid());
            productDTO.setTitle(product.getTitle());
            productDTO.setDescription(product.getDescription());
            productDTO.setImage(product.getImage());
            productDTO.setPrice(price);
            productDTO.setCategory(category);
            genericProductDTOS.add(productDTO);

        }
        return genericProductDTOS;
    }
    @Override
    public GenericProductDTO getProductById(UUID id) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
           throw new RuntimeException();
        Product prod = product.get();
        genericProductDTO.setId(id);
        genericProductDTO.setTitle(prod.getTitle());
        genericProductDTO.setDescription(prod.getDescription());
        genericProductDTO.setImage(prod.getImage());
        Category category = new Category();
        category.setUuid(prod.getCategory().getUuid());
        category.setName(prod.getCategory().getName());
        Price price = new Price();
        price.setUuid(prod.getPrice().getUuid());
        price.setPrice(prod.getPrice().getPrice());
        price.setCurrency(prod.getPrice().getCurrency());
        genericProductDTO.setCategory(category);
        genericProductDTO.setPrice(price);
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        Product product1 = new Product();
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        product1.setUuid(product.getId());
        product1.setCategory(product.getCategory());
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        Product newProduct = productRepository.save(product1);
        genericProductDTO.setId(newProduct.getUuid());
        genericProductDTO.setTitle(newProduct.getTitle());
        genericProductDTO.setDescription(newProduct.getDescription());
        genericProductDTO.setImage(newProduct.getImage());
        genericProductDTO.setCategory(newProduct.getCategory());
        genericProductDTO.setPrice(newProduct.getPrice());
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO product, UUID id) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        Optional<Product> product1 = productRepository.findById(id);
        if(product1.isEmpty())
            throw new RuntimeException();
        Product prod = product1.get();
        prod.setTitle(product.getTitle());
        prod.setImage(product.getImage());
        prod.setDescription(product.getDescription());
        prod.setCategory(product.getCategory());
        prod.setPrice(product.getPrice());
        Product savedProduct = productRepository.save(prod);
        genericProductDTO.setTitle(savedProduct.getTitle());
        genericProductDTO.setId(savedProduct.getUuid());
        genericProductDTO.setImage(savedProduct.getImage());
        genericProductDTO.setDescription(savedProduct.getDescription());
        Category category = new Category();
        category.setUuid(savedProduct.getCategory().getUuid());
        category.setName(savedProduct.getCategory().getName());
        genericProductDTO.setCategory(category);
        Price price = new Price();
        price.setUuid(savedProduct.getPrice().getUuid());
        price.setPrice(savedProduct.getPrice().getPrice());
        price.setCurrency(savedProduct.getPrice().getCurrency());
        genericProductDTO.setPrice(price);
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO deleteProductById(UUID id) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new RuntimeException();
        Product prod = product.get();
        genericProductDTO.setId(prod.getUuid());
        genericProductDTO.setImage(prod.getImage());
        genericProductDTO.setDescription(prod.getDescription());
        genericProductDTO.setTitle(prod.getTitle());
        Category category = new Category();
        category.setUuid(prod.getCategory().getUuid());
        category.setName(prod.getCategory().getName());
        genericProductDTO.setCategory(category);
        Price price = new Price();
        price.setUuid(prod.getPrice().getUuid());
        price.setPrice(prod.getPrice().getPrice());
        price.setCurrency(prod.getPrice().getCurrency());
        genericProductDTO.setPrice(price);
        productRepository.deleteById(UUID.fromString(id.toString()));
        //Return Deleted Product Info
        return genericProductDTO;
    }
}
