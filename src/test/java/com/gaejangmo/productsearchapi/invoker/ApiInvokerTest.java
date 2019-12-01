package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApiInvokerTest {
    @Autowired
    private ApiInvoker apiInvoker;

    @Test
    void 상품_검색() {
        // TODO mock
        ResponseEntity<ProductResponseDto> result = apiInvoker.getItem("맥북");
        assertNotNull(result);
    }
}