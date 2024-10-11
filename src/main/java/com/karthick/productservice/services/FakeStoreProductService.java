package com.karthick.productservice.services;

import com.karthick.productservice.dtos.FakeStoreProductResponseDto;
import com.karthick.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        String url = "https://fakestoreapi.com/products/"+id;
        FakeStoreProductResponseDto responseDto =
                restTemplate.getForObject(url,
                        FakeStoreProductResponseDto.class);

        System.out.println("Response DTO: " + responseDto);
        assert responseDto != null;
        return responseDto.toProduct();
    }
}
