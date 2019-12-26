package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.invoker.factory.NaverShoppingParamFactory;
import com.gaejangmo.productsearchapi.invoker.parser.SearchResultParser;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.json.simple.parser.ParseException;
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

@RefreshScope
@Component
public class ApiInvoker {
    private static final Logger logger = LoggerFactory.getLogger(ApiInvoker.class);

    private final RestTemplate restTemplate;

    @Value("${searchapi.naverid}")
    private String naverId;

    @Value("${searchapi.naversecret}")
    private String naverSecret;

    public ApiInvoker(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        try {
            return SearchResultParser.parse(result.getBody());
        } catch (ParseException e) {
            logger.info("Product API 파싱을 하지 못했습니다");
            return Collections.emptyList();
        }
    }
}