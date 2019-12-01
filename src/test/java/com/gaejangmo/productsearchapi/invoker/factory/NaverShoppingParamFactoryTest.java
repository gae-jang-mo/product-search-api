package com.gaejangmo.productsearchapi.invoker.factory;

import com.gaejangmo.productsearchapi.invoker.ApiParams;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

class NaverShoppingParamFactoryTest {
    private static final String PRODUCT_NAME = "query";
    private static final String RESULT_ROWS = "display";
    private static final String START_INDEX = "start";
    private static final String SORT_OPTION = "sort";
    private static final String CLIENT_ID = "X-Naver-Client-Id";
    private static final String CLIENT_SECRET = "X-Naver-Client-Secret";

    @Test
    void 키_발급() {
        HttpEntity<String> httpEntity = NaverShoppingParamFactory.createHttpEntity();

        assertThat(httpEntity.getHeaders().get(CLIENT_ID)).isNotNull();
        assertThat(httpEntity.getHeaders().get(CLIENT_SECRET)).isNotNull();
    }

    @Test
    void url_생성() throws URISyntaxException {
        String result = NaverShoppingParamFactory.getUrl(ApiParams.SHOP_API.getUrl(), "맥북");

        assertThat(new URI(result))
                .hasParameter(PRODUCT_NAME, "맥북")
                .hasParameter(RESULT_ROWS, "10")
                .hasParameter(START_INDEX, "1")
                .hasParameter(SORT_OPTION, "sim");
    }
}