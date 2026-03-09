package com.jahid.inventory_api.dto

import java.math.BigDecimal

data class ProductResponse(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val sku: String,
    val stock: Int,
    val categories: List<String>
)
