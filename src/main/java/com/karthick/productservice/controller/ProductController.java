package com.karthick.productservice.controller;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.commons.AuthenticationCommons;
import com.karthick.productservice.dtos.*;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService,
                             AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id)
            throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductResponseDto.from(product));
    }

    @GetMapping("/products/")
    public List<ProductResponseDto> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Product> productPage = productService.getAllProducts(pageNumber, pageSize, sortBy);

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            responseDtos.add(ProductResponseDto.from(product));
        }
        return responseDtos;
    }

    @PostMapping("/product/")
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
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
        if (!id.equals(product.getId())) {
            return ResponseEntity.badRequest().body("Product ID in the path and body must match.");
        }
        productService.partialUpdate(product);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        Product product = new Product();
        product.setId(id);
        productService.deleteProduct(product);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
