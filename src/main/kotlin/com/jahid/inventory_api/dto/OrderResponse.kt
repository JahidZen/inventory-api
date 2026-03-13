package com.jahid.inventory_api.dto

import com.jahid.inventory_api.enums.OrderStatus
import java.math.BigDecimal

data class OrderResponse(
    val orderId: Long,
    val email: String,
    val amount: BigDecimal,
    val status: OrderStatus,
    val orderedItems: List<OrderItemResponse>
)
