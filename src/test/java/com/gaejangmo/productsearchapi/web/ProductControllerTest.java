package com.gaejangmo.productsearchapi.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class ProductControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void 상품검색() {
        // TODO mock
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/search")
                        .queryParam("productName", "맥북")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                    .isOk()
                .expectBody()
                    .jsonPath("$.products")
                    .isNotEmpty();
    }
}

