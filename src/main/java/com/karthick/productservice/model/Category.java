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
    @Column(name = "category_name", unique = true)
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
