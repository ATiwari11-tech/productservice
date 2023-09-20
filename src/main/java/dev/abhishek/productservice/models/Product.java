package dev.abhishek.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;//url of the image
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="category")
    private Category category;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private Price price;
}
