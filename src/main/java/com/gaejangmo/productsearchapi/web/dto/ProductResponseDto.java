package com.gaejangmo.productsearchapi.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaejangmo.productsearchapi.model.product.NaverProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductResponseDto {

    @JsonProperty("title")
    private String productName;

    @JsonProperty("link")
    private String buyUrl;

    @JsonProperty("image")
    private String imageUrl;

    @JsonProperty("lprice")
    private long lowestPrice;

    @JsonProperty("hprice")
    private long highestPrice;

    @JsonProperty("mallName")
    private String mallName;

    @JsonProperty("productId")
    private long productId;

    @JsonProperty("productType")
    private NaverProductType naverProductType;

    public ProductResponseDto(String productName, String buyUrl, String imageUrl, long lowestPrice, long highestPrice, String mallName, long productId, NaverProductType naverProductType) {
        this.productName = productName;
        this.buyUrl = buyUrl;
        this.imageUrl = imageUrl;
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
        this.mallName = mallName;
        this.productId = productId;
        this.naverProductType = naverProductType;
    }
}
