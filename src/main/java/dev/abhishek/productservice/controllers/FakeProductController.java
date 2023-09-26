package dev.abhishek.productservice.controllers;

import dev.abhishek.productservice.dtos.GenericProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.services.FakeProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakeproducts")
public class FakeProductController {
//    @Autowired
    // field injection
    private FakeProductService fakeProductService;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public FakeProductController(FakeProductService fakeProductService) {
        this.fakeProductService = fakeProductService;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return fakeProductService.getAllProducts();
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
