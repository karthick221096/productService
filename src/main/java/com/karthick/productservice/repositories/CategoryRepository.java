package com.karthick.productservice.repositories;

import com.karthick.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
    Category save(Category category);
    List<Category> findAll();
}
