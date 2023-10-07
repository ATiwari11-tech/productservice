package dev.abhishek.productservice.controllers;


import dev.abhishek.productservice.dtos.ProductDto;
import dev.abhishek.productservice.exceptions.NotFoundException;
import dev.abhishek.productservice.services.ProductService;
import dev.abhishek.productservice.thirdpartyclients.productservice.fakestore.FakeStoryProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private FakeStoryProductServiceClient fakeStoryProductServiceClient;
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;
    @Test
    void returnNullWhenProductDoesntExist() throws NotFoundException {
        when(productService.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a0"))
                .thenReturn(null);
        ProductDto productDto = productController.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a0");

        assertNull(productDto);
    }
    @Test
    void shouldReturnTitleABCWithProductId1() throws NotFoundException {
        ProductDto productDto = new ProductDto();
        productDto.setTitle("ABC");
        when(productService.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a1"))//this id doesn't exist in DB
                .thenReturn(productDto);
        ProductDto productDto1 = productController.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a1");

        assertEquals("ABC",productDto1.getTitle());
    }
    @Test
    void returnProductWhenProductExists() throws NotFoundException {
        ProductDto productDto = new ProductDto();
//        productDto.setTitle("ABC");
        when(productService.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a1"))//this id doesn't exist in DB
                .thenReturn(productDto);
        assertEquals(productDto,productController.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a1"));
    }
    @Test
    void throwsExceptionWhenProductDoesntExist() throws NotFoundException {
        when(productService.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a1"))//this id doesn't exist in DB
                .thenReturn(null);

        assertThrows(NotFoundException.class,() -> productController.getProductById("c4550096-1596-432d-89c2-c6f3fb9954a1"));
    }
    @Test
    @DisplayName("1+1 equals 2")
    void testOnePlusOneEqualsTrue() throws NotFoundException {
        //this is between middle of unit test and integration test but neither unit test nor integration test
//        System.out.println("It is true");
//        assert(2 == 1+1);
//        assertEquals(11,1+1,"1+1 is not equal to 11");
//        assertNull(fakeStoryProductServiceClient.getProductById(101L));
        assertThrows(NotFoundException.class,() -> fakeStoryProductServiceClient.getProductById(101L));
    }
    @Test
    void additionShouldBeCorrect(){
        assert -1 + -1 == -2;
        assert -1 + 0 == -1;
        assert 1 + 0 == 1;
        assert 1 + 1 == 2;
    }
}

//Assertion framework
//->allows you to make checks