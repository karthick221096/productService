package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.dtos.FakeStoreProductRequestDto;
import com.karthick.productservice.dtos.FakeStoreProductResponseDto;
import com.karthick.productservice.dtos.ProductRequestDto;
import com.karthick.productservice.dtos.ProductResponseDto;
import com.karthick.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/"+id;
        FakeStoreProductResponseDto responseDto =
                restTemplate.getForObject(url,
                        FakeStoreProductResponseDto.class);

        if(responseDto == null){
            throw new ProductNotFoundException("product with id : "+id+" not found");
        }
        return responseDto.toProduct();
    }

    /**
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDtos = restTemplate
                .getForObject("https://fakestoreapi.com/products",FakeStoreProductResponseDto[].class);

        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto :responseDtos){
            productList.add(responseDto.toProduct());
        }
        return productList;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product createProduct(String title,String description, Double price, String imageUrl,String categoryName) {
        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();

        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(imageUrl);
        requestDto.setCategory(categoryName);

        FakeStoreProductResponseDto responseDto = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        requestDto,
                        FakeStoreProductResponseDto.class);
        return responseDto.toProduct();
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product partialUpdate(Product product) {
        HttpEntity<Product> FakeStoreProductRequestDto = new HttpEntity<>(product);

        ResponseEntity<FakeStoreProductResponseDto> responseEntity =
        restTemplate.exchange("https://fakestoreapi.com/products"+ product.getId(),
                HttpMethod.PATCH,
                FakeStoreProductRequestDto,
                FakeStoreProductResponseDto.class);

        FakeStoreProductResponseDto responseDto = responseEntity.getBody();
        assert responseDto != null;
        return responseDto.toProduct();
    }
}
