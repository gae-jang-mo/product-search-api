package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.invoker.factory.NaverShoppingParamFactory;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiInvoker {
    private RestTemplate restTemplate;

    public ApiInvoker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ProductResponseDto> getItem(String productName) {
        ResponseEntity<Object> result = restTemplate.exchange(
                NaverShoppingParamFactory.getUrl(ApiParams.SHOP_API.getUrl(), productName),
                HttpMethod.GET,
                NaverShoppingParamFactory.createHttpEntity(),
                Object.class);

        // TODO 도메인 정의
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}