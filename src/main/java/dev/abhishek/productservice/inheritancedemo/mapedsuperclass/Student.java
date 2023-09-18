package dev.abhishek.productservice.inheritancedemo.mapedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ms_students")
public class Student extends User{
    private Double psp;
    private Double attendance;

}
