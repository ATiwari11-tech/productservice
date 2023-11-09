package dev.abhishek.productservice.controllers;

import dev.abhishek.productservice.dtos.GetProductTitlesRequestDto;
import dev.abhishek.productservice.dtos.ProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.models.Product;
import dev.abhishek.productservice.security.JWTObject;
import dev.abhishek.productservice.security.TokenValidator;
import dev.abhishek.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private TokenValidator tokenValidator;
    // constructor injection
    public ProductController(ProductService productService,TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }


    // GET /products {}
    @GetMapping
    public List<ProductDto> getAllProducts(@RequestBody GetProductTitlesRequestDto requestDto) {
        List<String> uuids = requestDto.getUuids();
        return productService.getAllProducts(uuids);
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductsInCategory(@PathVariable("categoryId") String categoryId) throws NotFoundException {
        return productService.getProductsInCategory(categoryId);
    }

//    // localhost:8080/products/{id}
//    // localhost:8080/products/123
//    @GetMapping("{id}")
//    //Send Authorization in the headers
//    public ProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) authToken, @PathVariable("id") String id) throws NotFoundException {
//        ProductDto productDto = productService.getProductById(id);
//        if(productDto == null){
//            throw new NotFoundException("Product Id Doesn't Exist");
//        }
//        return productDto;
//    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    //Send Authorization in the headers
    public ProductDto getProductById(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") String id) throws NotFoundException {
        //Below lines of code should be done in API Gateway
        System.out.println("Auth Token:"+authToken);
        Optional<JWTObject> authTokenObjOptional;
        JWTObject authTokenObj = null;
        if(authToken != null){
            authTokenObjOptional = tokenValidator.validateToken(authToken);
            if(authTokenObjOptional.isEmpty()){
                //for Now allow it to run even for invalid token
                //ignore
            }
            authTokenObj = authTokenObjOptional.get();
            //Uptill here is the job of API Gateway
        }
        ProductDto productDto = productService.getProductById(id,authTokenObj.getUserId());
        if(productDto == null){
            throw new NotFoundException("Product Id Doesn't Exist");
        }
        return productDto;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public ProductDto updateProductById(@RequestBody ProductDto product,@PathVariable("id") String id) {
        return productService.updateProduct(product,id);
    }
}
