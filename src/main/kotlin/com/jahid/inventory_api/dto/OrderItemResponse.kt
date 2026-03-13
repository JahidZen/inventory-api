package com.jahid.inventory_api.dto

import java.math.BigDecimal

data class OrderItemResponse(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val priceAtPurchase: BigDecimal
)

