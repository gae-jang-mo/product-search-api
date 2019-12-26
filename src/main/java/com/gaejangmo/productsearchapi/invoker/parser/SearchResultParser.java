package com.gaejangmo.productsearchapi.invoker.parser;

import com.gaejangmo.productsearchapi.model.product.NaverProductType;
import com.gaejangmo.productsearchapi.web.dto.ProductResponseDto;
import lombok.experimental.UtilityClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class SearchResultParser {
    private static final String PRODUCT_NAME = "title";
    private static final String BUY_LINK = "link";
    private static final String IMAGE = "image";
    private static final String LOWEST_PRICE = "lprice";
    private static final String HIGHEST_PRICE = "hprice";
    private static final String MALL_NAME = "mallName";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_TYPE = "productType";
    private static final String TARGET_KEY = "items";
    private static final JSONParser PARSER = new JSONParser();

    public static List<ProductResponseDto> parse(String input) throws ParseException {
        JSONObject parse = (JSONObject) PARSER.parse(input);
        JSONArray items = (JSONArray) parse.get(TARGET_KEY);
        return parseProductsInGeneralPriceComparisonPrice(items);
    }

    private static List<ProductResponseDto> parseProductsInGeneralPriceComparisonPrice(JSONArray items) {
        return IntStream.range(0, items.size())
                .mapToObj(items::get)
                .map(item -> (JSONObject) item)
                .filter(jsonObject -> NaverProductType.isGeneralPriceComparisonPrice((String) jsonObject.get(PRODUCT_TYPE)))
                .map(SearchResultParser::createProductDto)
                .collect(Collectors.toList());
    }

    private static ProductResponseDto createProductDto(JSONObject jsonObject) {
        return new ProductResponseDto(
                HtmlTagRemover.parse((String) jsonObject.get(PRODUCT_NAME)),
                (String) jsonObject.get(BUY_LINK),
                (String) jsonObject.get(IMAGE),
                Integer.parseInt((String) jsonObject.get(LOWEST_PRICE)),
                Integer.parseInt((String) jsonObject.get(HIGHEST_PRICE)),
                (String) jsonObject.get(MALL_NAME),
                Long.parseLong((String) jsonObject.get(PRODUCT_ID)),
                NaverProductType.getType(Integer.parseInt((String) jsonObject.get(PRODUCT_TYPE))));
    }
}
