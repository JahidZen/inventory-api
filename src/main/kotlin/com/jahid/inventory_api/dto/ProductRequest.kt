package com.jahid.inventory_api.dto

import java.math.BigDecimal

data class ProductRequest(
    var name: String,
    var price: BigDecimal,
    var sku: String,
    var stock: Int,
    var categoryIds: Set<Long>
)
