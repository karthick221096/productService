package com.karthick.productservice.services;

import com.karthick.productservice.ProductNotFoundException;
import com.karthick.productservice.dtos.FakeStoreProductRequestDto;
import com.karthick.productservice.dtos.FakeStoreProductResponseDto;
import com.karthick.productservice.dtos.ProductRequestDto;
import com.karthick.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "products", key = "#id")
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

    @Override
    @Cacheable(
            value = "products",
            key = "'all:' + #pageNumber + ':' + #pageSize + ':' + #sortBy"
    )
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy) {
        FakeStoreProductResponseDto[] responseDtos = restTemplate
                .getForObject("https://fakestoreapi.com/products", FakeStoreProductResponseDto[].class);

        List<Product> productList = new ArrayList<>();
        if (responseDtos != null) {
            for (FakeStoreProductResponseDto responseDto : responseDtos) {
                productList.add(responseDto.toProduct());
            }
        }
        // FakeStore API doesn't support paging natively, so we simulate it
        int start = Math.min(pageNumber * pageSize, productList.size());
        int end = Math.min(start + pageSize, productList.size());
        return new PageImpl<>(productList.subList(start, end), PageRequest.of(pageNumber, pageSize), productList.size());
    }

    /**
     * @param product
     * @return
     */
    @Override
    @CachePut(value = "products", key = "#result.id")
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
    @CachePut(value = "products", key = "#result.id")
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

    /**
     * @param product
     */
    @Override
    @CacheEvict(value = "products", key = "#product.id")
    public void deleteProduct(Product product) {

    }
}
