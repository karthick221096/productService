package com.karthick.productservice.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BaseModel {
    private long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
}
