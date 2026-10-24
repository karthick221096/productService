package com.karthick.productservice.repositories;

import com.karthick.productservice.model.Category;
import com.karthick.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);

    Optional<Product> findById(Long ID);
    List<Product> findAll();

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_categoryNameEquals(String title);
}
