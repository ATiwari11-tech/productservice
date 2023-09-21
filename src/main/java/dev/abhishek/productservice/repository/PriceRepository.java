package dev.abhishek.productservice.repository;

import dev.abhishek.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Long> {

}
