package dev.abhishek.productservice.controllers;

import dev.abhishek.productservice.dtos.GenericProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.security.TokenValidator;
import dev.abhishek.productservice.services.FakeProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fakeproducts")
public class FakeProductController {
//    @Autowired
    // field injection
    private FakeProductService fakeProductService;
    private TokenValidator tokenValidator;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public FakeProductController(FakeProductService fakeProductService,TokenValidator tokenValidator) {
        this.fakeProductService = fakeProductService;
        this.tokenValidator = tokenValidator;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
//    @GetMapping
//    public List<GenericProductDto> getAllProducts() {
//        return fakeProductService.getAllProducts();
//    }
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos =  fakeProductService.getAllProducts();
        if(productDtos.isEmpty()){
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }
//        List<GenericProductDto> genericProductDtos = new ArrayList<>();//added to fail the test
//        for(GenericProductDto genericProductDto:productDtos){//added to fail the test
//            genericProductDtos.add(genericProductDto);
//
//        }
//        genericProductDtos.remove(genericProductDtos.get(0));//to fail the test
        return new ResponseEntity<>(productDtos,HttpStatus.OK);//this will pass the test
//        return new ResponseEntity<>(genericProductDtos,HttpStatus.OK);//added to fail the test
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return fakeProductService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                fakeProductService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return fakeProductService.createProduct(product);
    }

//    @PutMapping("{id}")
//    public void updateProductById() {
//
//    }
}
