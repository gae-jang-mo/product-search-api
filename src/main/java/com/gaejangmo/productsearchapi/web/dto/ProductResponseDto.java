package com.gaejangmo.productsearchapi.web.dto;

import com.gaejangmo.productsearchapi.model.product.NaverProductType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ProductResponseDto {
    private String productName;
    private String buyUrl;
    private String imageUrl;
    private long lowestPrice;
    private long highestPrice;
    private String mallName;
    private long productId;
    private NaverProductType naverProductType;
}
