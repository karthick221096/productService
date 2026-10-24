package com.karthick.productservice.controller;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.dtos.ProductResponseDto;
import com.karthick.productservice.model.Category;
import com.karthick.productservice.model.Product;
import com.karthick.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @MockBean
    @Qualifier("ProductDbService")
    private ProductService productService;
    @Autowired
    private ProductController productController;

//    @Test
//    void test_getProductByID_validID_ReturnProductResponseDTO() throws ProductNotFoundException {
//        //arrange
//        Product mockProduct = new Product();
//        mockProduct.setId(1L);
//        mockProduct.setTitle("abc");
//        mockProduct.setImageUrl("imageUrl");
//        mockProduct.setPrice(20.0);
//        mockProduct.setDescription("description");
//        Category mockCategory = new Category();
//        mockCategory.setCategoryName("categoryName");
//        mockProduct.setCategory(mockCategory);
//
//        when(productService.getProductById(1L)).thenReturn(mockProduct);
//        //act
//        ProductResponseDto responseDto = productController.getProductById(1L);
//        //assert
//        assertNotNull(responseDto);
//        assertEquals("categoryName", responseDto.getCategoryName());
//        assertEquals(20.0, responseDto.getPrice());
//        assertEquals("abc", responseDto.getTitle());
//    }

//    @Test
//    void test_getProductByID_returnNull() throws ProductNotFoundException {
//        when(productService.getProductById(1L)).thenReturn(null);
//        ProductResponseDto responseDto = productController.getProductById(1l);
//        assertNull(responseDto);
//    }

}
