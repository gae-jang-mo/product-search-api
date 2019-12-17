package com.gaejangmo.productsearchapi.invoker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiParams {
    SHOP_API("https://openapi.naver.com/v1/search/shop.json");

    private final String url;
}
