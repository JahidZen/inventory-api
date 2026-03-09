package com.jahid.inventory_api.mapper

import com.jahid.inventory_api.dto.ProductResponse
import com.jahid.inventory_api.model.Product

fun Product.toResponse(): ProductResponse {
    return ProductResponse(
        id = id,
        name = name,
        price = price,
        sku = sku,
        stock = stock,
        categories = categories.map { it.name }
    )
}