package com.karthick.productservice.dtos;

import com.karthick.productservice.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private String categoryName;

    public static ProductResponseDto from(Product product){
        if(product == null){
            return null;
        }
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategoryName(product.getCategory().getCategoryName());
        return productResponseDto;
    }
}
