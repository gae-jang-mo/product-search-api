package com.gaejangmo.productsearchapi.web;

import com.gaejangmo.productsearchapi.invoker.ApiInvoker;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    private ApiInvoker apiInvoker;

    public SearchController(ApiInvoker apiInvoker) {
        this.apiInvoker = apiInvoker;
    }

    @GetMapping("/api/v1/search")
    public ResponseEntity<List<ProductResponseDto>> search(@RequestParam String productName) throws ParseException {
        // TODO invoke 의 exception 을 잡아서 controller advice 로 처리해보자. parseException
        return apiInvoker.getItem(productName);
    }
}
