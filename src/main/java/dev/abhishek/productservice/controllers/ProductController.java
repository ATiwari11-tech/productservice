package dev.abhishek.productservice.controllers;

import dev.abhishek.productservice.dtos.ExceptionDTO;
import dev.abhishek.productservice.dtos.GenericProductDTO;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    //localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);

    }
//    @DeleteMapping("{id}")
//    public GenericProductDTO deleteProductById(@PathVariable("id") Long id){
//        return productService.deleteProductById(id);
//    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.NOT_FOUND
        );
    }
    @PostMapping()
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public GenericProductDTO updateProductById(@RequestBody GenericProductDTO product,@PathVariable("id") Long id){
        return productService.updateProductById(product,id);
    }
//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
//        System.out.println("Not Found Exception happened");
//        return new ResponseEntity(
//                new ExceptionDTO(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND
//        );
//    }
}
