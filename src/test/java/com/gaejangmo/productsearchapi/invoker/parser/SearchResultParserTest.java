package com.gaejangmo.productsearchapi.invoker.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDtos;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class SearchResultParserTest {
    // 주의!! Spring context 를 띄우지 않으므로 Bean 로딩이 되지 않는다.
    // ObjectMapperConfig 의 설정된 mapper 설정과 일치시켜야 한다.
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 상품_json_역직렬화() throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("dummy/search_api_result.json")).getFile());
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(file);
        JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
        String resultJsonString = jsonObject.toJSONString();

        ProductResponseDtos productResponseDtos = objectMapper.readValue(resultJsonString, ProductResponseDtos.class);

        assertThat(productResponseDtos.getProductDtos().size()).isEqualTo(10);
    }
}