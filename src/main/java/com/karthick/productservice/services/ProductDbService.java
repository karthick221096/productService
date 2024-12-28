package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.model.Category;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.repositories.CategoryRepository;
import com.karthick.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ProductDbService")
public class ProductDbService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDbService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * @param id
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("product is not available");
        }
        return productOptional.get();
    }

    /**
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * @param title
     * @param description
     * @param price
     * @param imageUrl
     * @param categoryName
     * @return
     */
    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName) {
        Product product = new Product();

        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

//        Category category = new Category();
////        category.setName(categoryName); what if the category is already exist

        product.setCategory(getCategoryFromDb(categoryName));

        return productRepository.save(product);
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product partialUpdate(Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("there is no existing product with respective ID");
        }

        Product productToBeUpdate = productOptional.get();

        if(product.getTitle()!=null){
            productToBeUpdate.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null){
            productToBeUpdate.setDescription(product.getDescription());
        }
        if(product.getPrice()!=null){
            productToBeUpdate.setPrice(product.getPrice());
        }
        if(product.getImageUrl()!=null){
            productToBeUpdate.setImageUrl(product.getImageUrl());
        }
        if(product.getCategory()!=null){
            productToBeUpdate.setCategory(product.getCategory());
        }

        return productRepository.save(productToBeUpdate);
    }

    /**
     * @param product
     */
    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    private Category getCategoryFromDb(String categoryName){
        Optional<Category> categoryOptional =categoryRepository.findByCategoryName(categoryName);
        if(categoryOptional.isEmpty()){
            Category category = new Category();
            category.setCategoryName(categoryName);
            return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }
}
