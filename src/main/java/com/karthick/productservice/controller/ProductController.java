package com.karthick.productservice.controller;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.dtos.ErrorDto;
import com.karthick.productservice.dtos.FakeStoreProductResponseDto;
import com.karthick.productservice.dtos.ProductRequestDto;
import com.karthick.productservice.dtos.ProductResponseDto;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("ProductDbService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id ) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }

    @GetMapping("/products/")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products= productService.getAllProducts();

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for (Product product:products){
            responseDtos.add(ProductResponseDto.from(product));
        }
        return responseDtos;
    }

    @PostMapping("/product/")
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        Product product = productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                Double.valueOf(productRequestDto.getPrice()),
                productRequestDto.getImage(),
                productRequestDto.getCategory());

        return ProductResponseDto.from(product);
    }

@PatchMapping("/product/{id}")
public ResponseEntity<String> updateProduct(
        @PathVariable Long id,
        @RequestBody Product product) throws ProductNotFoundException {
    // Ensure the ID in the URL matches the ID in the Product object, if applicable
    if (!id.equals(product.getId())) {
        return ResponseEntity.badRequest().body("Product ID in the path and body must match.");
    }

    // Perform the update
    productService.partialUpdate(product);

    return ResponseEntity.ok("Product updated successfully");
}

}
