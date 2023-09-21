package dev.abhishek.productservice.inheritancedemo.mapedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ms_tas")
public class TA extends User{
    private Double avgRating;

}
