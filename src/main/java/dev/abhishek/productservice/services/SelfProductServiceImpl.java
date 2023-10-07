package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.ProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.models.Category;
import dev.abhishek.productservice.models.Product;
import dev.abhishek.productservice.repositories.CategoryRepository;
import dev.abhishek.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Primary//Make this as primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto getProductById(String id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if(product.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }
        Product prod = product.get();
        prod.getPrice();//Needed coz of lazy loading
        ProductDto productDto = new ProductDto();
        productDto.setTitle(prod.getTitle());
        productDto.setDescription(prod.getDescription());
        productDto.setPrice(prod.getPrice());
        productDto.setImage(prod.getImage());
        return productDto;
    }

    @Override
    public List<ProductDto> getProductsInCategory(String categoryid) throws NotFoundException {
        Optional<Category> optionalCategory  = categoryRepository.findById(UUID.fromString(categoryid));
        if(optionalCategory.isEmpty())
            throw new RuntimeException();
        Category category = optionalCategory.get();
        List<Product> products  = category.getProducts();
        List<ProductDto> productDTOs = new ArrayList<>();
        for(Product product:products){
            ProductDto productDto = new ProductDto();
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setImage(product.getImage());
            productDTOs.add(productDto);
        }
        return productDTOs;
    }

    @Override
    public ProductDto createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        newProduct.setPrice(product.getPrice());
        Category category = product.getCategory();
        newProduct.setCategory(category);
        Product savedProduct = productRepository.save(newProduct);
        ProductDto newProductDto = new ProductDto();
        newProductDto.setTitle(savedProduct.getTitle());
        newProductDto.setDescription(savedProduct.getDescription());
        newProductDto.setImage(savedProduct.getImage());
        newProductDto.setPrice(savedProduct.getPrice());
        return newProductDto;
    }

    @Override
    public List<ProductDto> getAllProducts(List<String> categoryUUIDs) {
        List<UUID> uuids = new ArrayList<>();
        for(String categoryId:categoryUUIDs){
            uuids.add(UUID.fromString(categoryId));
        }
        List<Category> categories = categoryRepository.findAllById(uuids);
        List<Product> products = productRepository.findAllByCategoryIn(categories);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:products){
            ProductDto productDto = new ProductDto();
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto deleteProduct(String id) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty())
            throw new RuntimeException();
        Product product = optionalProduct.get();
        //Product Details to Return
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productRepository.delete(product);
        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String id) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty())
            throw new RuntimeException();
        Product product = optionalProduct.get();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        Product savedProduct  = productRepository.save(product);
        ProductDto newProductDto = new ProductDto();
        newProductDto.setTitle(savedProduct.getTitle());
        newProductDto.setDescription(savedProduct.getDescription());
        newProductDto.setImage(savedProduct.getImage());
        newProductDto.setPrice(savedProduct.getPrice());
        return newProductDto;
    }
}
