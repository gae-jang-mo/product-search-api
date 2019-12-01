package com.gaejangmo.productsearchapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductsDto {
    private List<ProductDto> products;

    public int size() {
        return products.size();
    }
}
