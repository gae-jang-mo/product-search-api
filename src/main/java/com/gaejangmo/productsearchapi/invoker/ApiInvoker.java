package com.gaejangmo.productsearchapi.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaejangmo.productsearchapi.invoker.factory.NaverShoppingParamFactory;
import com.gaejangmo.productsearchapi.invoker.parser.SearchResultParser;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RefreshScope
@Component
public class ApiInvoker {
    private static final Logger logger = LoggerFactory.getLogger(ApiInvoker.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${searchapi.naverid}")
    private String naverId;

    @Value("${searchapi.naversecret}")
    private String naverSecret;

    public ApiInvoker(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<List<ProductResponseDto>> getItem(final String productName) {
        ResponseEntity<String> result = restTemplate.exchange(
                NaverShoppingParamFactory.getUrl(ApiParams.SHOP_API.getUrl(), productName),
                HttpMethod.GET,
                NaverShoppingParamFactory.createHttpEntityWithNaverKeys(naverId, naverSecret),
                String.class);

        return ResponseEntity.ok(parse(result));
    }

    private List<ProductResponseDto> parse(final ResponseEntity<String> result) {
        ProductResponseDtos products;
        try {
            products = objectMapper.readValue(Objects.requireNonNull(result.getBody()), ProductResponseDtos.class);
        } catch (JsonProcessingException e) {
            logger.error("네이버 상품 데이터를 파싱하지 못했습니다 errorMsg {} body {}", e.getMessage(), result.getBody());
            return Collections.emptyList();
        }
        return SearchResultParser.parse(products.getProductDtos());
    }
}