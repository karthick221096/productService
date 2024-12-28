package com.karthick.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Category")
public class Category extends BaseModel{
    @Column(name = "category_name")
    private String name;
    @Column(name = "category_description")
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
