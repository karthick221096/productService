package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy);
    Product createProduct(String title, String description, Double price, String imageUrl, String categoryName);
    Product partialUpdate(Product product) throws ProductNotFoundException;
    void deleteProduct(Product product) throws ProductNotFoundException;
}
