package dev.abhishek.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="tbc_mentors")
public class Mentor extends User {
    private Double avgRating;
}
