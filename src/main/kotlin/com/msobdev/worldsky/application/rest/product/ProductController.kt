package com.msobdev.worldsky.application.rest.product

import com.msobdev.worldsky.application.rest.product.model.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.logging.Logger

@RestController
class ProductController {
    companion object {
        private val LOG = Logger.getLogger(ProductController::class.java.name)
    }

    @GetMapping("/api/v1/product/{id}")
    fun findOne(@PathVariable id: Int): Mono<ProductResponse> {
        LOG.info("sth")
        return Mono.just(ProductResponse("skyprod", id))
    }
}