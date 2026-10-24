package com.karthick.productservice.service;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.repositories.ProductRepository;
import com.karthick.productservice.services.ProductDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductDbServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductDbService productDbService;
    @Test
    void shouldReturnProductWithValidID() throws ProductNotFoundException {
        Product mockProduct = new Product();
        mockProduct.setImageUrl("imageURL");
        mockProduct.setTitle("Tittle");
        mockProduct.setPrice(20.0);
        mockProduct.setDescription("Description");

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product product = productDbService.getProductById(1L);

        Assertions.assertNotNull(product);
        Assertions.assertEquals("Tittle",product.getTitle());
        Assertions.assertEquals("imageURL", product.getImageUrl());
    }
}
