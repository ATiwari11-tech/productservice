package dev.abhishek.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abhishek.productservice.dtos.GenericProductDto;
import dev.abhishek.productservice.services.FakeProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FakeProductController.class) //Only initialize dependencies that can be reached from Product Controller i.e. those Services,
// Controlller, Repository which it needs and do not initialize unnecessary dependencies

public class ProductControllerWebMVCTest {//Real functional tests we are writing here
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FakeProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());//Coming from FakeProductService
        mockMvc.perform(get("/fakeproducts"))
                .andExpect(status().is(404))
                .andExpect(content().string("[]"));
    }
    @Test
    void returnsListOfProductsWhenProductExists() throws Exception {
        ArrayList<GenericProductDto> products = new ArrayList<>();
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());
        when(
                productService.getAllProducts()
        ).thenReturn(products);
        mockMvc.perform(get("/fakeproducts"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }
    @Test
    void  createProductShouldCreateANewProduct() throws Exception {
        GenericProductDto productToCreate = new GenericProductDto();
        productToCreate.setTitle("iPhone 15 Pro");
        productToCreate.setImage("some image");
        productToCreate.setCategory("Electronics");
        productToCreate.setPrice(150000);
        productToCreate.setDescription("Best iPhone");
        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setId(1001L);
        expectedProduct.setTitle("iPhone 15 Pro");
        expectedProduct.setImage("some image");
        expectedProduct.setCategory("Electronics");
        expectedProduct.setPrice(150000);
        expectedProduct.setDescription("Best iPhone");
        when(
                productService.createProduct(any())
        ).thenReturn(
                expectedProduct
        );
        mockMvc.perform(
                post("/fakeproducts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate))
        ).andExpect(
                content().string(objectMapper.writeValueAsString(expectedProduct))
        ).andExpect(status().is(200))
                .andExpect(jsonPath("$.student.name",is("Naman")))//here matchers coming from hamcrest
                .andExpect(jsonPath("$.length()",is(2)));
    }
}
