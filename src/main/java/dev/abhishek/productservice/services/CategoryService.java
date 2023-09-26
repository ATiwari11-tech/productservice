package dev.abhishek.productservice.services;

import dev.abhishek.productservice.dtos.CategoryDto;
import dev.abhishek.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(String uuid);
    List<String> getProductTitles(List<String> categoryUUIDs);
    List<CategoryDto> getAllCategories(List<String> categoryUUIds);
}
