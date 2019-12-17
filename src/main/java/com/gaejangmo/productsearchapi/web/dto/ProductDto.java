package com.gaejangmo.productsearchapi.web.dto;

import com.gaejangmo.productsearchapi.model.product.NaverProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String title;
    private String link;
    private String image;
    private int lowestPrice;
    private int highestPrice;
    private String mallName;
    private long productId;
    private NaverProductType naverProductType;
}