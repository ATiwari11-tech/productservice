package dev.abhishek.productservice.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.NoRepositoryBean;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseModel{
    String currency;
    double price;
}
