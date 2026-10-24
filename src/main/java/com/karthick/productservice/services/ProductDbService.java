package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.model.Category;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.repositories.CategoryRepository;
import com.karthick.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("ProductDbService")
public class ProductDbService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDbService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return productOptional.get();
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setCategory(getCategoryFromDb(categoryName));
        return productRepository.save(product);
    }

    @Override
    public Product partialUpdate(Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + product.getId());
        }

        Product productToUpdate = productOptional.get();
        if (product.getTitle() != null) productToUpdate.setTitle(product.getTitle());
        if (product.getDescription() != null) productToUpdate.setDescription(product.getDescription());
        if (product.getPrice() != null) productToUpdate.setPrice(product.getPrice());
        if (product.getImageUrl() != null) productToUpdate.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) productToUpdate.setCategory(product.getCategory());

        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + product.getId());
        }
        Product productToDelete = productOptional.get();
        productToDelete.setIsDeleted(true);
        productRepository.save(productToDelete);
    }

    private Category getCategoryFromDb(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        if (categoryOptional.isEmpty()) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }
}
