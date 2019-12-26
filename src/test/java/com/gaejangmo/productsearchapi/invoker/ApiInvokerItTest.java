package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.model.product.NaverProductType;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class ApiInvokerItTest {
    @Autowired
    private ApiInvoker apiInvoker;

    @Test
    void name() {
        ResponseEntity<List<ProductResponseDto>> result = apiInvoker.getItem("맥북");

        for (ProductResponseDto productResponseDto : Objects.requireNonNull(result.getBody())) {
            assertSame(productResponseDto.getNaverProductType(), NaverProductType.GENERAL_PRICE_COMPARISON_PRICE);
        }
    }
}
