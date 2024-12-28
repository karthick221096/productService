package com.karthick.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;

@Getter
@Setter
@Entity
@Table(name = "Product")
@ComponentScan
public class Product extends BaseModel{
    private String title;
    @Column(name = "product_description")
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne
    private Category category;
}
