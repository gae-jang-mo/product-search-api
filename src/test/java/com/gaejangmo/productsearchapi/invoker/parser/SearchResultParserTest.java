package com.gaejangmo.productsearchapi.invoker.parser;

import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class SearchResultParserTest {
    @Test
    void product_dto_파싱() throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("dummy/search_api_result.json")).getFile());
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(file);
        JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
        List<ProductResponseDto> parse = SearchResultParser.parse(jsonObject.toJSONString());

        assertThat(parse.size()).isEqualTo(10);
    }
}