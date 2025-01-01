package com.karthick.productservice;

import com.karthick.productservice.model.Category;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.repositories.CategoryRepository;
import com.karthick.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductserviceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    public void getProductByCalingTwoMethods(){
        Optional<Category> category = categoryRepository.findByCategoryName("Furniture");
        System.out.println(category.get());
        List<Product> products = productRepository.findByCategory(category.get());
    }

    @Test
    public void getProdcutsByCallingOneMethod(){
        List<Product> products = productRepository.findByCategory_categoryNameEquals("Smart LED Bulb");
        System.out.println(products);
    }

    @Test
    public void getCategory(){
        List<Category> categories = categoryRepository.findAll();
        System.out.println(categories);
    }

}
