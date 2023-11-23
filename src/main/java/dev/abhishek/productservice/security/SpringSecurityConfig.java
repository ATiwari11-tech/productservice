package dev.abhishek.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        //UnComment below if you want to enable authentication for mentioned endpoints
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/fakeproducts").hasAuthority("admin")///fakeproducts endpoint require
//                        // authentication as well as role admin
//                        .anyRequest().authenticated()//other endpoints like /fakeproductst/1
//                        // only require authentication
//                )
        //Below one is custom to disable authentication for search endpoint for now
        http
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/fakeproducts").hasAuthority("admin")///fakeproducts endpoint require
                                // authentication as well as role admin
                                .anyRequest().permitAll()//other endpoints like /fakeproductst/1
                        // only require authentication
                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
                        jwtConfigurer -> {
                            jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
                        }
                ))
                .cors().disable()//Needed for POST Request, Remove this if you enable authentication in the very first line
        .csrf().disable();//Needed for POST Request,Remove this if you enable authentication in the very first line

                // Form login handles the redirect to the login page from the
                // authorization server filter chain
//                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
