package dev.abhishek.productservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JWTObject {
    private String email;
    private String userId;
    private Date createdAt;
    private Date expiryAt;
    private List<Role> roles = new ArrayList<>();
}
