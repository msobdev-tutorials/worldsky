package com.msobdev.worldsky.application.rest.product

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.random.Random

@AutoConfigureWebTestClient
@SpringBootTest
@ActiveProfiles("integration")
class ProductControllerIT(
    @Autowired private val webClient: WebTestClient
) {
    companion object {
        const val PRODUCT_URI = "/api/v1/product/"
    }

    private var productId: String = ""

    @BeforeEach
    internal fun setUp() {
        productId = Random.nextInt().toString()
    }

    @Test
    fun getProductWithUnauthorized() {
        webClient
            .get()
            .uri{ uri -> uri.path(PRODUCT_URI)
                .path(productId)
                .build()
            }
            .exchange()
            .expectStatus().isUnauthorized
    }

    @Test
    fun getProductWithOk() {
        webClient
            .get()
            .uri { uri -> uri.path(PRODUCT_URI)
                    .path(productId)
                    .build()
            }.headers { httpHeaders -> httpHeaders.setBasicAuth("username", "password") }
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.name").isEqualTo("skyprod")
            .jsonPath("$.id").isEqualTo(productId)
    }
}