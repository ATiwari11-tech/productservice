package dev.abhishek.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    /**
     * Calls user service to validate the token
     * if token is not valid then returns Optional is empty
     * else optional contains all the data in the payload
     * @param token
     * @return
     */
    public Optional<JWTObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        return Optional.empty();
    }
}
