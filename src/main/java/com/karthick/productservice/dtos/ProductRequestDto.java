package com.karthick.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;


@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Price is mandatory")
    private String price;
    @NotBlank(message = "Category is mandatory")
    private String category;
    private String description;
    private String image;
}
