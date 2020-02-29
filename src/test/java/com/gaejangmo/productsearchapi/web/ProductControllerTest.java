package com.gaejangmo.productsearchapi.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void 상품검색() {
        // TODO mock 테스트만 할까? 빌드시 test 전략에 따라 통합 테스트를 제외 하든지 논의가 필요해 보임
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/products")
                        .queryParam("productName", "맥북")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}

