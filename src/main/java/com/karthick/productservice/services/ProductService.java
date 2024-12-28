package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.model.Product;

import java.util.List;


public interface ProductService<Prodcut> {
    public Product getProductById(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product createProduct(String title,String description, Double price, String imageUrl,String categoryName);
    public Product partialUpdate(Product product) throws ProductNotFoundException;
    public void deleteProduct(Product product);
}
