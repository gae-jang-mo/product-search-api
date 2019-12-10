package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.invoker.factory.NaverShoppingParamFactory;
import com.gaejangmo.productsearchapi.invoker.parser.SearchResultParser;
import com.gaejangmo.productsearchapi.web.dto.ProductsDto;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@Component
public class ApiInvoker {
    private RestTemplate restTemplate;

    @Value("${searchapi.naverid}")
    private String naverId;

    @Value("${searchapi.naversecret}")
    private String naverSecret;

    public ApiInvoker(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ProductsDto> getItem(String productName) throws ParseException {
        ResponseEntity<String> result = restTemplate.exchange(
                NaverShoppingParamFactory.getUrl(ApiParams.SHOP_API.getUrl(), productName),
                HttpMethod.GET,
                NaverShoppingParamFactory.createHttpEntityWithNaverKeys(naverId, naverSecret),
                String.class);

        // TODO 파싱 exchange 말고 getForObject로 바꿔야하남
        ProductsDto parse = SearchResultParser.parse(result.getBody());

        // TODO 도메인 정의
        return new ResponseEntity<>(parse, HttpStatus.OK);
    }
}