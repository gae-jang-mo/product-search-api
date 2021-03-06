package com.gaejangmo.productsearchapi.model.product;

import com.gaejangmo.productsearchapi.model.product.exception.ProductTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum NaverProductType {
    // 일반 상품
    GENERAL_PRICE_COMPARISON_PRICE(1),
    GENERAL_PRICE_NON_MATCHING_GENERAL_PRODUCT(2),
    GENERAL_PRICE_COMPARISON_MATCHING_GENERAL_PRODUCT(3),

    // 중고 상품
    USED_PRICE_COMPARISON_PRICE(4),
    USED_PRICE_NON_MATCHING_GENERAL_PRODUCT(5),
    USED_PRICE_COMPARISON_MATCHING_GENERAL_PRODUCT(6),

    // 단종 상품
    DISCONTINUED_PRICE_COMPARISON_PRICE(7),
    DISCONTINUED_PRICE_NON_MATCHING_GENERAL_PRODUCT(8),
    DISCONTINUED_PRICE_COMPARISON_MATCHING_GENERAL_PRODUCT(9),

    // 판매 예정 상품
    SCHEDULED_DISCONTINUED_PRICE_COMPARISON_PRICE(10),
    SCHEDULED_DISCONTINUED_PRICE_NON_MATCHING_GENERAL_PRODUCT(11),
    SCHEDULED_DISCONTINUED_PRICE_COMPARISON_MATCHING_GENERAL_PRODUCT(12);

    private static final List<NaverProductType> GENERAL_PRODUCT_TYPES =
            List.of(GENERAL_PRICE_COMPARISON_PRICE, GENERAL_PRICE_NON_MATCHING_GENERAL_PRODUCT, GENERAL_PRICE_COMPARISON_MATCHING_GENERAL_PRODUCT);

    private final int type;

    public static NaverProductType getType(int type) {
        return Arrays.stream(values())
                .filter(naverProductType -> naverProductType.type == type)
                .findAny()
                .orElseThrow(() -> new ProductTypeNotFoundException("제품 코드를 찾을 수 없습니다"));
    }

    public static boolean isGeneralProduct(NaverProductType type) {
        return GENERAL_PRODUCT_TYPES.contains(type);
    }
}
