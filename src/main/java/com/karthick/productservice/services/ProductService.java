package com.karthick.productservice.services;

import com.karthick.productservice.model.Product;

import java.util.List;


public interface ProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(String title,String description, Double price, String imageUrl,String categoryName);
    public Product partialUpdate(Product product);
}
