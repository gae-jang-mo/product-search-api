package com.gaejangmo.productsearchapi.invoker.parser;

import com.gaejangmo.productsearchapi.model.product.NaverProductType;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SearchResultParser {
    public static List<ProductResponseDto> parse(List<ProductResponseDto> productDtos) {
        return parseProductsInGeneralPriceComparisonPrice(productDtos);
    }

    private static List<ProductResponseDto> parseProductsInGeneralPriceComparisonPrice(List<ProductResponseDto> productDtos) {
        return productDtos.stream()
                .filter(productDto -> NaverProductType.isGeneralProduct(productDto.getNaverProductType()))
                .collect(Collectors.toList());
    }
}
