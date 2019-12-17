package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.invoker.dummy.TestData;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ApiInvokerTest {
    @Mock
    private ApiInvoker apiInvoker;

    @Test
    void 상품_검색() throws ParseException {
        given(apiInvoker.getItem("맥북"))
                .willReturn(new ResponseEntity(TestData.RESPONSE_DTOS, HttpStatus.OK));

        ResponseEntity<List<ProductResponseDto>> result = apiInvoker.getItem("맥북");

        assertThat(Objects.requireNonNull(result.getBody()).size()).isEqualTo(10);
    }
}