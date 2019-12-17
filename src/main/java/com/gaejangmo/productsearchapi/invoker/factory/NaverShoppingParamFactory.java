package com.gaejangmo.productsearchapi.invoker.factory;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

@UtilityClass
public class NaverShoppingParamFactory {
    private static final String PRODUCT_NAME = "query";
    private static final String RESULT_ROWS = "display";
    private static final String START_INDEX = "start";
    private static final String SORT_OPTION = "sort";
    private static final String CLIENT_ID = "X-Naver-Client-Id";
    private static final String CLIENT_SECRET = "X-Naver-Client-Secret";

    public static HttpEntity<String> createHttpEntityWithNaverKeys(final String naverId, final String naverSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CLIENT_ID, naverId);
        headers.set(CLIENT_SECRET, naverSecret);
        return new HttpEntity<>(headers);
    }

    public static String getUrl(String url, String productName) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(PRODUCT_NAME, productName)
                .queryParam(RESULT_ROWS, "10")
                .queryParam(START_INDEX, "1")
                .queryParam(SORT_OPTION, "sim")
                .build(false)                       // 한글이 깨져서 encoded 를 false
                .toString();
    }
}
