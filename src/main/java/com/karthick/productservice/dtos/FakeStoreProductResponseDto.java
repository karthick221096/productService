package com.karthick.productservice.dtos;

import com.karthick.productservice.model.Category;
import com.karthick.productservice.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
        private Long id;
        private String title;
        private String price;
        private String category;
        private String description;
        private String image;

        public Product toProduct(){
                Product product = new Product();
                product.setId(this.id);
                product.setTitle(this.title);
                product.setPrice(Double.valueOf(this.price));
                product.setImageUrl(this.image);
                product.setDescription(this.description);

                Category category1 = new Category();
                category1.setName(this.category);
                product.setCategory(category1);
                return product;
        }

}
