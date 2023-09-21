package dev.abhishek.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_students")
@PrimaryKeyJoinColumn(name="user_id")
public class Student extends User {
    private Double psp;
    private Double attendance;

}
