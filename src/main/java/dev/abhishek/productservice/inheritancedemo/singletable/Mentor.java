package dev.abhishek.productservice.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="st_mentors")
@DiscriminatorValue(value="3")
public class Mentor extends User {
    private Double avgRating;
}
