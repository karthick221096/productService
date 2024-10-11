package com.karthick.productservice.controller;

import com.karthick.productservice.dtos.ProductResponseDto;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id ){
        Product product = productService.getProductById(id);
        System.out.println(product);
        return ProductResponseDto.from(product);
    }
}
