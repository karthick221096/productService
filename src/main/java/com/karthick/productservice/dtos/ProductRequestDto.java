package com.karthick.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;
}
