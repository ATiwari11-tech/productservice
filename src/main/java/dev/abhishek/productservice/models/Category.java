package dev.abhishek.productservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel {
    private String name;
//    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
@OneToMany(mappedBy = "category")
    List<Product> products;

}
