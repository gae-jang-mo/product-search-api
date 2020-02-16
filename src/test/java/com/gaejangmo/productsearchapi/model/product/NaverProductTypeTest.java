package com.gaejangmo.productsearchapi.model.product;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NaverProductTypeTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void 일반_판매_상품_필터링(String type) {
        assertTrue(NaverProductType.isGeneralSellingProduct(type));
    }
}