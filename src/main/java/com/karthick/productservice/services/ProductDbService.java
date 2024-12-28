package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component(value = "ProductDbService")
public class ProductDbService implements ProductService{
    /**
     * @param id
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        return null;
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
        return null;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product partialUpdate(Product product) {
        return null;
    }

    /**
     * @param o
     */
    @Override
    public void deleteproduct(Object o) {

    }
}
