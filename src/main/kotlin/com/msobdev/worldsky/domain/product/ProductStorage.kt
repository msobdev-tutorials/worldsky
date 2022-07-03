package com.msobdev.worldsky.domain.product

import com.msobdev.worldsky.domain.product.model.Product

interface ProductStorage {
    fun getProduct(id: String): Product
}