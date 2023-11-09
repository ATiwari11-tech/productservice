package dev.abhishek.productservice.thirdpartyclients.productservice.fakestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FakeStoryProductServiceClientTest {
//    @Autowired
//    private RestTemplateBuilder restTemplateBuilder;
//    @Test
//    void testNonExistingProductReturnsNull(){
//        //this is real integration test
//        //not unit test because it has dependency on third party api
////        RestTemplate restTemplate = restTemplateBuilder.build();
////        ResponseEntity<FakeStoreProductDto> response =
////                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
////
////        FakeStoreProductDto fakeStoreProductDto = response.getBody();
////        assertNull(fakeStoreProductDto);
//    }
}