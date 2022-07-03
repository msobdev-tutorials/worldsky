package com.msobdev.worldsky.application.rest.product

import com.msobdev.worldsky.application.rest.product.model.ProductResponse
import com.msobdev.worldsky.domain.product.ProductService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@AllArgsConstructor
@RestController
class ProductController(private val productService: ProductService) {
    companion object {
        private val LOG = Logger.getLogger(ProductController::class.java.name)
    }

    @GetMapping("/api/v1/product/{id}")
    suspend fun findOne(@PathVariable id: Int): ProductResponse {
        LOG.info("sth")
        return ProductResponse("skyprod", id)
    }
}