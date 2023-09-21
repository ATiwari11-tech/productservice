package dev.abhishek.productservice.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_students")
@DiscriminatorValue(value="1")
public class Student extends User {
    private Double psp;
    private Double attendance;

}
