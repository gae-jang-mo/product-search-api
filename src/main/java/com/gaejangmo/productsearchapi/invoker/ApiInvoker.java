package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.invoker.factory.NaverShoppingParamFactory;
import com.gaejangmo.productsearchapi.invoker.parser.SearchResultParser;
import com.gaejangmo.productsearchapi.web.dto.ProductsDto;
import org.json.simple.parser.ParseException;
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

    public ResponseEntity<ProductsDto> getItem(String productName) throws ParseException {
        ResponseEntity<String> result = restTemplate.exchange(
                NaverShoppingParamFactory.getUrl(ApiParams.SHOP_API.getUrl(), productName),
                HttpMethod.GET,
                NaverShoppingParamFactory.createHttpEntity(),
                String.class);

        // TODO 파싱 exchange 말고 getForObject로 바꿔야하남
        ProductsDto parse = SearchResultParser.parse(result.getBody());

        // TODO 도메인 정의
        return new ResponseEntity<>(parse, HttpStatus.OK);
    }
}