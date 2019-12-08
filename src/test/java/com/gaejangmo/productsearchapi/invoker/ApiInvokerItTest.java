package com.gaejangmo.productsearchapi.invoker;

import com.gaejangmo.productsearchapi.web.dto.ProductsDto;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
public class ApiInvokerItTest {
    @Autowired
    private ApiInvoker apiInvoker;

    @Test
    void name() throws ParseException {
        ResponseEntity<ProductsDto> productsDto = apiInvoker.getItem("맥북");

        assertThat(Objects.requireNonNull(productsDto.getBody()).size()).isEqualTo(10);
    }
}
