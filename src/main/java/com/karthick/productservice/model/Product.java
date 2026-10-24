package com.karthick.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product extends BaseModel{
    private String title;
    @Column(name = "product_description")
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne
    private Category category;
}
