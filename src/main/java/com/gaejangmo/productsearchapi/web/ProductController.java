package com.gaejangmo.productsearchapi.web;

import com.gaejangmo.productsearchapi.invoker.ApiInvoker;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ApiInvoker apiInvoker;

    public ProductController(final ApiInvoker apiInvoker) {
        this.apiInvoker = apiInvoker;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<ProductResponseDto>> search(@RequestParam String productName) {
        return apiInvoker.getItem(productName);
    }
}
