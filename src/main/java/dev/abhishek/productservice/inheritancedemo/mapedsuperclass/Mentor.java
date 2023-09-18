package dev.abhishek.productservice.inheritancedemo.mapedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="ms_mentors")
public class Mentor extends User{
    private Double avgRating;
}
