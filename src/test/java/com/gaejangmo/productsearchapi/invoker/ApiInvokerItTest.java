package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApiInvokerItTest {
    @Autowired
    private ApiInvoker apiInvoker;

    @Test
    void name() throws ParseException {
        ResponseEntity<List<ProductResponseDto>> result = apiInvoker.getItem("맥북");

        assertThat(Objects.requireNonNull(result.getBody()).size()).isEqualTo(10);
    }
}
