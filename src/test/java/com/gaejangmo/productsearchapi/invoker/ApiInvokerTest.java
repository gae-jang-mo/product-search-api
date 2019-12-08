package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.invoker.dummy.TestData;
import com.gaejangmo.productsearchapi.web.dto.ProductsDto;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("dev")
class ApiInvokerTest {
    @Mock
    private ApiInvoker apiInvoker;

    @Test
    void 상품_검색() throws ParseException {
        given(apiInvoker.getItem("맥북"))
                .willReturn(new ResponseEntity<>(TestData.productsDto(), HttpStatus.OK));

        ResponseEntity<ProductsDto> result = apiInvoker.getItem("맥북");

        assertThat(Objects.requireNonNull(result.getBody()).size()).isEqualTo(10);
    }
}