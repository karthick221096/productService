package com.karthick.productservice.model;

import java.util.List;

public class Category extends BaseModel{
    private String name;
    private String description;
    private List<Product> productList;
}
