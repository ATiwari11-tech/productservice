package dev.abhishek.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbc_tas")
public class TA extends User {
    private Double avgRating;

}
